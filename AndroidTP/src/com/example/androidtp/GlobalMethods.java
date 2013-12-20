package com.example.androidtp;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.androidtp.model.MediaLoaderAsync_task;
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
			Log.v(tag, "isOnline");
			
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
	
			new MediaLoaderAsync_task().execute(MediaManager.getInstance().getURL(), MediaManager.getInstance().getDirectorypath(), MediaManager.getInstance().getFILENAME());
		} else
		{
			Toast.makeText(this.getBaseContext(), "Réseau non disponible, veuillez vérifier votre connexion internet",
					3).show();

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
