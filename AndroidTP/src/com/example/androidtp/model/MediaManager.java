package com.example.androidtp.model;

import android.os.Environment;



public class MediaManager {
	
private static final String URL="http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?type=medias&serial=AAA";
private static final String DirectoryPath=Environment.getExternalStorageDirectory()+"/media/";
private static final String FILENAME="Medias.xml";
private static MediaManager mInstance = null;


private MediaManager(){
super();
new MediaLoaderAsync_task().execute(URL,DirectoryPath,FILENAME );
}


public static MediaManager getInstance(){
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





}
