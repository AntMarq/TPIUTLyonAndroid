package com.example.androidtp.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.androidtp.GlobalMethods;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MediaLoaderAsync_task extends AsyncTask<String, Integer, String>
{

	private String tag = "MediaLoaderAsync_task";
	private ObjMediaInfo newMediaObj = null;
	private ProgressBar pb;
	private Context context;
	private GlobalMethods application;
	private ProgressDialog dialog;
	private boolean firstLaunch;
	
	public MediaLoaderAsync_task(Context context)
	{
		super();
		this.context = context;	
	}

	@Override
	protected void onPreExecute()
	{
		firstLaunch = context.getSharedPreferences("PREFERENCE", context.MODE_PRIVATE).getBoolean("firstrun", true); ;
		Log.v(tag, "onPreExecute" + firstLaunch );
		if(firstLaunch)
		{
			 dialog= new ProgressDialog(context);
	         dialog.setIndeterminate(true);
	         dialog.setCancelable(false);
	         dialog.setMessage("Downloading! Please wait...!");
	         dialog.show();    
		}
	};

	@Override
	protected String doInBackground(String... params)
	{
		Log.v(tag, "doInBackground Principale");
		String sResponse;
		StringBuilder sb = new StringBuilder();
			
		try
		{
			/**
			 * If folder Media contains file, delete files when the application
			 * is update
			 */

			int count = 0;
			URL url = new URL(params[0]);
			/**
			 * Download file media.xml in folder Media
			 */
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(params[1] + params[2]);
			byte data[] = new byte[1024];
			long total = 0;

			while ((count = is.read(data)) != -1)
			{
				total += count;
				fos.write(data, 0, count);
			}
			is.close();
			fos.close();

			HttpURLConnection conection = (HttpURLConnection) url.openConnection();
			Log.v(tag, "conection" + conection.getResponseCode());
			conection.connect();
			if (conection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
				while ((sResponse = in.readLine()) != null)
				{
					sb = sb.append(sResponse);
				}
				return sb.toString();
			}

		}
		catch (Exception e)
		{
			// TODO faire une gestion d'erreur
			Log.e("MediaLoaderAsync_task", e.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result)
	{
		Log.v(tag, "onPostExecute" + result);
		application = (GlobalMethods)context.getApplicationContext ();
		if (result != null)
		{

			try
			{
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				xpp.setInput(new StringReader(result));

				int eventType = xpp.getEventType();

				while (eventType != XmlPullParser.END_DOCUMENT)
				{

					String name = null;
					switch (eventType)
					{
						case XmlPullParser.START_DOCUMENT :
							Log.v(tag, "eventType = " + eventType);
							break;
						case XmlPullParser.START_TAG :
							name = xpp.getName();

							if (name.equalsIgnoreCase("media"))
							{
								newMediaObj = new ObjMediaInfo();
								newMediaObj.set_name(xpp.getAttributeValue(null, "name"));
								newMediaObj.set_type(xpp.getAttributeValue(null, "type"));
								newMediaObj.set_url(xpp.getAttributeValue(null, "path"));
								newMediaObj.set_version(xpp.getAttributeValue(null, "versionCode"));

							}

							break;
						case XmlPullParser.END_TAG :

							name = xpp.getName();

							if (name.equalsIgnoreCase("media") && newMediaObj != null)
							{

								if (newMediaObj.get_type().equalsIgnoreCase("video"))
								{
									if (MediaManager.getInstance().videoArrayContainMediaObject(newMediaObj) == false)
										MediaManager.getInstance().getVideoMedia().add(newMediaObj);
								}
								else if (newMediaObj.get_type().equalsIgnoreCase("audio"))
								{
									if (MediaManager.getInstance().sonArrayContainMediaObject(newMediaObj) == false)
										MediaManager.getInstance().getAudioMedia().add(newMediaObj);
								}
								else if (newMediaObj.get_type().equalsIgnoreCase("image"))
								{
									if (MediaManager.getInstance().imageArrayContainMediaObject(newMediaObj) == false)
										MediaManager.getInstance().getPictureMedia().add(newMediaObj);
								}
								else
								{
									if (MediaManager.getInstance().textArrayContainMediaObject(newMediaObj) == false)
										MediaManager.getInstance().getTexteMedia().add(newMediaObj);

									String textpath = newMediaObj.get_url();
									String namefile = newMediaObj.get_name();

									new GetTextFile().execute(textpath, namefile);

								}
							}
					}
					eventType = xpp.next();
				}
				MediaManager.getInstance().triggerObservers();

				/**
				 * Parse XML finish
				 */
				 
			}
			catch (XmlPullParserException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			Toast.makeText (application.getBaseContext(), "Erreur lors de la recuperation des données", 3).show ();
			if(firstLaunch)
			{
				dialog.dismiss();
			}		
			Log.e(tag, "Une erreur est survenue pendant la recuperation du flux RSS");

		}

	}

	/*
	 * Classe de chargement du fichier text Necessaire de parser le xml afin
	 * d'acquérir le path de l'url pour le dl du fichier text
	 */
	private class GetTextFile extends AsyncTask<String, Integer, String>
	{
		private String ObjName;
	
		@Override
		protected String doInBackground(String... params)
		{
			Log.v(tag, "doInBackground Seconddaire");
			ObjName = params[1];
			int count;
			String sResponse;
			StringBuilder sb = new StringBuilder();
			try
			{

				URL url2 = new URL(MediaManager.getInstance().getBaseUrl() + params[0]);

				InputStream is = url2.openStream();
				File testDirectory = new File(MediaManager.getInstance().getDirectorypath());
				FileOutputStream fos = new FileOutputStream(testDirectory + "/" + params[1] + ".txt");
				byte data[] = new byte[1024];
				long total = 0;

				while ((count = is.read(data)) != -1)
				{
					total += count;
					fos.write(data, 0, count);
				}
				is.close();
				fos.close();

				HttpURLConnection conexion2 = (HttpURLConnection) url2.openConnection();
				conexion2.connect();

				if (conexion2.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					BufferedReader in = new BufferedReader(new InputStreamReader(conexion2.getInputStream()));
					while ((sResponse = in.readLine()) != null)
					{
						sb = sb.append(sResponse);
					}
					return sb.toString();
				}
			}
			catch (Exception e)
			{
				if(firstLaunch)
				{
					dialog.dismiss();
				}		
				Log.e("ERROR DOWNLOADING", "Unable to download" + e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result)
		{
			if (result != null)
			{
				for (ObjMediaInfo obj : MediaManager.getInstance().getTexteMedia())
				{
					if (obj.get_name().equals(ObjName))
					{
						obj.set_content(result);
					}

				}
			}
			if(firstLaunch)
			{
				dialog.dismiss();
			}			
		}
	}
}