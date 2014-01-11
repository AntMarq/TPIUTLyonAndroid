package com.example.androidtp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.androidtp.loader.MediaLoaderAsync_task;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GlobalMethods extends Application
{

	/**
	 * Class Global for the application
	 */

	private ObjMediaInfo selectedObjMediaInfo;
	private String tag = "GlobalMethods";

	@Override
	public void onCreate()
	{
		super.onCreate();
		/**
		 * Create global configuration and initialize ImageLoader with this
		 * configuration
		 */
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
		ImageLoader.getInstance().init(config);
	}
	
	public void loadOfflineData()
	{
		ObjMediaInfo newMediaObj = null;
		
		File file = new File(MediaManager.getInstance().getDirectorypath()+ MediaManager.getInstance().getFILENAME());
		if(file.length() > 0)
		{
			Log.v(tag, "chargement fichier locale");
				try
				{
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser parser = factory.newPullParser();
		
					
					FileInputStream fis = new FileInputStream(file);
					parser.setInput(new InputStreamReader(fis));
		
					int eventType = parser.getEventType();
		
					while (eventType != XmlPullParser.END_DOCUMENT)
					{
						String name = null;
						switch (eventType)
						{
							case XmlPullParser.START_DOCUMENT :
								break;
		
							case XmlPullParser.START_TAG :						
								name = parser.getName();
		
								if (name.equalsIgnoreCase("media"))
								{
									newMediaObj = new ObjMediaInfo();
									newMediaObj.set_name(parser.getAttributeValue(null, "name"));
									newMediaObj.set_type(parser.getAttributeValue(null, "type"));
									newMediaObj.set_url(parser.getAttributeValue(null, "path"));
									newMediaObj.set_version(parser.getAttributeValue(null, "versionCode"));
								}
		
								break;
							case XmlPullParser.END_TAG :
		
								name = parser.getName();
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
									}
								}
						}
						eventType = parser.next();
					}
					MediaManager.getInstance().triggerObservers();
		
				}
				catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (XmlPullParserException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	/**
	 * Check internet connexion
	 * 
	 */
	public boolean isOnline(Context currentActivity)
	{
		ConnectivityManager cm = (ConnectivityManager) currentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected())
		{
			return true;
		}

		return false;

	}

	/**
	 * Refresh only it's internet connexion is OK
	 */

	public void refreshOnline()
	{
		if (this.isOnline(this.getBaseContext()) == true)
		{
			Log.v(tag, "refreshOnline");
			
			File dir = new File(MediaManager.getInstance().getDirectorypath()) ;
			Log.v(tag, "before delete file");
			if (dir.isDirectory() && dir.list().length != 0)
			{
				Log.v(tag, "delete file" + dir.list().length);
				String[] children = dir.list();
		        for (int i = 0; i < children.length; i++) 
		        {
		            new File(dir, children[i]).delete();
		        }
			}
	
			new MediaLoaderAsync_task(this.getBaseContext()).execute(MediaManager.getInstance().getURL(), MediaManager.getInstance().getDirectorypath(), MediaManager.getInstance().getFILENAME());
		} 
		else
		{
			Toast.makeText(this.getBaseContext(), "Réseau non disponible, veuillez vérifier votre connexion internet",3).show();

		}
	}

	/**
	 * check directory path create the directory if null
	 * 
	 * @param path
	 * @return
	 */
	public static boolean ManageDirectory(String path)
	{

		if (path == null)
			return false;
		File file = new File(path);
		if (file.exists() && file.isDirectory())
		{			
			return true;
		} else
			return file.mkdir();
	}

	public ObjMediaInfo getSelectedObjMediaInfo()
	{
		return selectedObjMediaInfo;
	}

	public void setSelectedObjMediaInfo(ObjMediaInfo selectedObjMediaInfo)
	{
		this.selectedObjMediaInfo = selectedObjMediaInfo;
	}

}
