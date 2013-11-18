package com.example.androidtp;

import java.io.File;

import com.example.androidtp.model.MediaManager;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GlobalMethods extends Application {
	
	public boolean isOnline(Context currentActivity) {
		ConnectivityManager cm = (ConnectivityManager) currentActivity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			return true;
		}

		return false;

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

}
