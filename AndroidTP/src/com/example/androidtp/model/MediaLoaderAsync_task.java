package com.example.androidtp.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.AsyncTask;
import android.util.Log;

public class MediaLoaderAsync_task extends AsyncTask<String, Integer, String> {

	private String tag = "MediaLoaderAsync_task";
	ObjMediaInfo newMediaObj = null;

	public MediaLoaderAsync_task() {
		super();
		Log.v(tag, "MediaLoaderAsync_task");
	}

	@Override
	protected void onPreExecute() {

	};

	@Override
	protected void onProgressUpdate(Integer... progress) {

	}

	@Override
	protected String doInBackground(String... params) {
		String sResponse;
		StringBuilder sb = new StringBuilder();

		try {
			int count = 0;
			URL url = new URL(params[0]);
			HttpURLConnection conection = (HttpURLConnection) url
					.openConnection();
			conection.connect();
			if (conection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						conection.getInputStream()));
				while ((sResponse = in.readLine()) != null) {
					sb = sb.append(sResponse);
				}
				return sb.toString();
			}

			// getting file length
			// int lenghtOfFile = conection.getContentLength();

			// input stream to read file - with 8k buffer
			InputStream input = new BufferedInputStream(url.openStream(), 8192);

			// Output stream to write file
			OutputStream output = new FileOutputStream(params[1] + params[2]);
			byte data[] = new byte[1024];
			long total = 0;
			while ((count = input.read(data)) != -1) {
				total += count;
				output.write(data, 0, count);
			}
			// flushing output
			output.flush();
			// closing streams
			output.close();

			input.close();

		} catch (Exception e) {
			// TODO faire une gestion d'erreur
			Log.e("MediaLoaderAsync_task", e.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		if (result != null) {

			try {
				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				xpp.setInput(new StringReader(result));

				int eventType = xpp.getEventType();
				
				
				int eventType = xpp.getEventType();
		//		ObjMediaInfo newMediaObj = null;
				 
			    while (eventType != XmlPullParser.END_DOCUMENT)
			    {
		    	
		            String name = null;
		            switch (eventType)
		            {
		                case XmlPullParser.START_DOCUMENT:
		                    break;
		                case XmlPullParser.START_TAG:
		                    name = xpp.getName(); 
	            		                    
	                    if (name.equalsIgnoreCase("media"))
	                    {	                  		                    	
	                    	newMediaObj = new ObjMediaInfo(); 
	                    	newMediaObj.set_name(xpp.getAttributeValue(null, "name"));
	                    	newMediaObj.set_type(xpp.getAttributeValue(null, "type"));
	                    	newMediaObj.set_url(xpp.getAttributeValue(null, "path"));
	                    	newMediaObj.set_version(xpp.getAttributeValue(null, "versionCode"));
	                    	
	                    	
	                    	/*Log.v(tag, "name" + newMediaObj.get_name() +" "+
	                    	"type" + newMediaObj.get_type() +" "+
	                    	"path" + newMediaObj.get_url() +" "+
	                    	"versionCode" + newMediaObj.get_version());*/
	                    } 
	                   
	                    break;
	                	case XmlPullParser.END_TAG:
	                	
	                    name = xpp.getName();
	                    
	                    if (name.equalsIgnoreCase("media") && newMediaObj != null)
	                    {
	                    	/*MediaManager.getInstance().getListMedia().add(newMediaObj);
	                    	Log.v(tag, "listMedia" + MediaManager.getInstance().getListMedia().size());*/
	                    	
	                    	if(newMediaObj.get_type().equalsIgnoreCase("video"))
	                    	{
	                    		if(MediaManager.getInstance().videoArrayContainMediaObject(newMediaObj)==false)
	                    		MediaManager.getInstance().getVideoMedia().add(newMediaObj);
	                    	}
	                    	else if (newMediaObj.get_type().equalsIgnoreCase("audio"))
	                    	{
	                    		if(MediaManager.getInstance().sonArrayContainMediaObject(newMediaObj)==false)
	                    		MediaManager.getInstance().getAudioMedia().add(newMediaObj);
	                    	}
	                    	else if(newMediaObj.get_type().equalsIgnoreCase("image"))
	                    	{
	                    		if(MediaManager.getInstance().imageArrayContainMediaObject(newMediaObj)==false)
	                    		MediaManager.getInstance().getPictureMedia().add(newMediaObj);
	                    	}
	                    	else
	                    	{
	                    		if(MediaManager.getInstance().textArrayContainMediaObject(newMediaObj)==false)
	                    			MediaManager.getInstance().getTexteMedia().add(newMediaObj);
	                    		
	                    		String textpath = newMediaObj.get_url();
	                    		String namefile = newMediaObj.get_name();
	                    		                    		
	                    		new GetTextFile ().execute(textpath,namefile);
	                    		
	                    	}
	                    }       
		            }
		            eventType = xpp.next();
		        }
			    //reussite du chargement;
			   
			    MediaManager.getInstance().triggerObservers();
			    
			    //fin du traitement du xml
			    
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
		} else {
			Log.e(tag,
					"Une erreur est survenue pendant la recuperation du flux RSS");
		}
	}
	}
	
/*Classe de chargement du fichier text 
 * Necessaire de parser le xml afin d'acquérir le path de l'url pour le dl du fichier text
 * */ 	
	private class GetTextFile extends AsyncTask<String, Integer, String>
	{
		private String ObjName;
		
		@Override
		protected String doInBackground(String... params) 
		{
			ObjName = params[1];
			int count; 
			String sResponse;
			StringBuilder sb = new StringBuilder();
			try {

				URL url2 = new URL(MediaManager.getInstance().getBaseUrl()
						+ params[0]);
				Log.v(tag, "test url = " + url2);
				HttpURLConnection conexion2 = (HttpURLConnection) url2
						.openConnection();

				conexion2.connect();
				if (conexion2.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(conexion2.getInputStream()));
					while ((sResponse = in.readLine()) != null) {
						sb = sb.append(sResponse);
					}
					return sb.toString();
				}
				InputStream is = url2.openStream();
				File testDirectory = new File(MediaManager.getInstance()
						.getDirectorypath());
				if (!testDirectory.exists()) {
					testDirectory.mkdir();
				}
				FileOutputStream fos = new FileOutputStream(testDirectory + "/"
						+ params[1] + ".txt");
				byte data[] = new byte[1024];
				long total = 0;

				while ((count = is.read(data)) != -1) {
					total += count;
					fos.write(data, 0, count);
				}
				is.close();
				fos.close();
			} catch (Exception e) {
				Log.e("ERROR DOWNLOADING",
						"Unable to download" + e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Log.v(tag, "onPostExecute" + result);
			if (result != null) {
				Log.v(tag, "result parse TextFile" + result);
				Log.v(tag, "test object" + newMediaObj.get_name());
				
				
				for(ObjMediaInfo obj: MediaManager.getInstance().getTexteMedia())
				{
					if(obj.get_name().equals(ObjName))
					{
						newMediaObj.set_content(result);
					}
					
				}
			}			
		}			
	}	
}	