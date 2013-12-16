package com.example.androidtp;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.androidtp.model.MediaLoaderAsync_task;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GlobalMethods extends Application 
{
	private ObjMediaInfo selectedObjMediaInfo;
	private String tag = "GlobalMethods" ;
	
	
	@Override
    public void onCreate() 
	{
          super.onCreate();
          // Create global configuration and initialize ImageLoader with this configuration
          ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())        
          .build();        
          ImageLoader.getInstance().init(config);
     }

	public boolean isOnline(Context currentActivity) {
		ConnectivityManager cm = (ConnectivityManager) currentActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			return true;
		}

		return false;

	}
	
	public void refreshOnline()
	{
		if(this.isOnline(this.getBaseContext()) == true )
		{
			new MediaLoaderAsync_task().execute(MediaManager.getInstance().getURL(),null,null);
		}
		else
		{
			Toast.makeText (this.getBaseContext(), "Réseau non disponible, veuillez vérifier votre connexion internet", 3).show ();
			
		}
	}
	/**
	 * check directory path
	 * create the directory if null
	 * @param path
	 * @return
	 */
	public static boolean ManageDirectory(String path){
		if(path==null)
			return false;
		File file=new File(path);
		if(file.exists()&&file.isDirectory()){
			return true;}
		else
			return file.mkdir();
	}
	
	public ObjMediaInfo getSelectedObjMediaInfo() {
		return selectedObjMediaInfo;
	}
	public void setSelectedObjMediaInfo(ObjMediaInfo selectedObjMediaInfo) {
		this.selectedObjMediaInfo = selectedObjMediaInfo;
	}

	
	
	
}
