package com.example.androidtp;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.androidtp.model.ObjMediaInfo;

public class GlobalMethods extends Application 
{
	private ObjMediaInfo selectedObjMediaInfo;

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
	public ObjMediaInfo getSelectedObjMediaInfo() {
		return selectedObjMediaInfo;
	}
	public void setSelectedObjMediaInfo(ObjMediaInfo selectedObjMediaInfo) {
		this.selectedObjMediaInfo = selectedObjMediaInfo;
	}
	
	
}
