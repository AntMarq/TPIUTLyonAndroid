package com.example.androidtp.model;

import java.util.ArrayList;

import android.content.Context;
import android.os.Environment;



public class MediaManager {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static final String URL="http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?type=medias&serial=AAA";
private static final String DirectoryPath=Environment.getExternalStorageDirectory()+"/media/";
private static final String FILENAME="Medias.xml";
private static MediaManager mInstance = null;
private static ArrayList<ObjMediaInfo> listMedia;


private MediaManager()
{
	super();
	listMedia = new ArrayList<ObjMediaInfo>();
	new MediaLoaderAsync_task().execute(URL,DirectoryPath,FILENAME );
}

public ArrayList<ObjMediaInfo> getLocalType (String localType)
{
	ArrayList<ObjMediaInfo> localMedia = new ArrayList<ObjMediaInfo>() ;
	for (ObjMediaInfo objMediaInfo : this.getListMedia()) 
	{
		if(objMediaInfo.get_type().equalsIgnoreCase(localType))
		{
			localMedia.add(objMediaInfo);
		}
	}
	return localMedia ;
}

public static MediaManager getInstance()
{
	if(mInstance == null)
	{
		mInstance = new MediaManager();
	}
return mInstance;
}

/**
 * @return the mInstance
 */
public static MediaManager getmInstance() {
	return mInstance;
}

/**
 * @param mInstance the mInstance to set
 */
public static void setmInstance(MediaManager mInstance) {
	MediaManager.mInstance = mInstance;
}


/**
 * @return the directorypath
 */
public static String getDirectorypath() {
	return DirectoryPath;
}


public ArrayList<ObjMediaInfo> getListMedia() {
	return listMedia;
}


public void setListMedia(ArrayList<ObjMediaInfo> listMedia) {
	MediaManager.listMedia = listMedia;
}
}
