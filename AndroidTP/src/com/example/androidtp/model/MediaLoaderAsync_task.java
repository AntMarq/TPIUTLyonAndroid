package com.example.androidtp.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
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

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.androidtp.OnXMLLoadFinishedListener;

public class MediaLoaderAsync_task extends AsyncTask<String, Integer, String> {

	
	String tag = "MediaLoaderAsync_task";
	private OnXMLLoadFinishedListener listener;
	private Context context;
	private int loadingResult ;
	

	public MediaLoaderAsync_task() 
	{
		
	}
	public void setOnXMLLoadFinishedListener(OnXMLLoadFinishedListener listener){
		  this.listener = listener;
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPreExecute() {

	};

	@Override
	protected void onProgressUpdate(Integer... progress) {

	}



	@Override
	protected String doInBackground(String... params) 
	{
		String sResponse;
		StringBuilder sb = new StringBuilder ();
		// Log.v("MediaLoaderAsync_Task","params[0]= "+params[0]);
		try {
			int count = 0;
			URL url = new URL(params[0]);
			HttpURLConnection conection = (HttpURLConnection) url.openConnection();
			conection.connect();
			if (conection.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
				while ((sResponse = in.readLine ()) != null)
				{
					sb = sb.append (sResponse);
				}
				return sb.toString ();
			}
			
			
			// getting file length
			int lenghtOfFile = conection.getContentLength();
			
			// input stream to read file - with 8k buffer
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
			

				// Output stream to write file
				OutputStream output = new FileOutputStream(params[1]+params[2]);
				byte data[] = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1) 
				{
					total += count;
					// publishing the progress....
					// After this onProgressUpdate will be called
					// publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					// writing data to file
					output.write(data, 0, count);
				}
				// flushing output
				output.flush();
				// closing streams
				output.close();
				
				input.close();

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
		if (result != null)
		{
			
			try 
			{
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				factory.setNamespaceAware(true);
				XmlPullParser xpp = factory.newPullParser();
				xpp.setInput(new StringReader(result));
				
				
				int eventType = xpp.getEventType();
				ObjMediaInfo newMediaObj = null;
				 
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
	                    	
	                    	Log.v(tag, "name" + newMediaObj.get_name() +" "+
	                    	"type" + newMediaObj.get_type() +" "+
	                    	"path" + newMediaObj.get_url() +" "+
	                    	"versionCode" + newMediaObj.get_version());
	                    } 
	                   
	                    break;
	                	case XmlPullParser.END_TAG:
	                	
	                    name = xpp.getName();
	                    
	                    if (name.equalsIgnoreCase("media") && newMediaObj != null)
	                    {
	                    	MediaManager.getInstance().getListMedia().add(newMediaObj);
	                    	Log.v(tag, "listMedia" + MediaManager.getInstance().getListMedia().size());
	                    }       
		            }
		            eventType = xpp.next();
		        }
			    //reussite du chargement;
			    loadingResult = 1;
			   
			    
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
		
		}
		else
		{
			Log.e (tag, "Une erreur est survenue pendant la recuperation du flux RSS");
			 loadingResult = 0;
		}
		 listener.onXMLDataReady(loadingResult);
	}
}	
