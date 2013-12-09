package com.example.androidtp.model;

import java.util.ArrayList;
import java.util.Observable;

import android.os.Environment;



public class MediaManager extends Observable{
	
/**
	 * 
	 */

private final String FILENAME="Medias.xml";
private final String URL="http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?type=medias&serial=AAA";
private final String DirectoryPath=Environment.getExternalStorageDirectory()+"/media/";
private final long serialVersionUID = 1L;

private static MediaManager mInstance = null;
private  ArrayList<ObjMediaInfo> videoMedia;
private  ArrayList<ObjMediaInfo> audioMedia;
private  ArrayList<ObjMediaInfo> pictureMedia;
private  ArrayList<ObjMediaInfo> texteMedia;
private ObjMediaInfo selectedObjMediaInfo;


private MediaManager()
{
	super();
	videoMedia = new ArrayList<ObjMediaInfo>();
	audioMedia = new ArrayList<ObjMediaInfo>();
	pictureMedia = new ArrayList<ObjMediaInfo>();
	texteMedia = new ArrayList<ObjMediaInfo>();	
	
	new MediaLoaderAsync_task().execute(URL,DirectoryPath,FILENAME );	
}

/*public ArrayList<ObjMediaInfo> getLocalType (String localType)
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
}*/



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
	public String getDirectorypath() {
		return DirectoryPath;
	}

public void triggerObservers() {
setChanged();
    notifyObservers();
}

public ArrayList<ObjMediaInfo> getVideoMedia() {
	return videoMedia;
}

public boolean videoArrayContainMediaObject(ObjMediaInfo mediaobject){
	for(ObjMediaInfo obj :this.getVideoMedia()){
		if(obj.compareTo(mediaobject)==0)
			return true;
	}
	return false;
}

public boolean textArrayContainMediaObject(ObjMediaInfo mediaobject){
	for(ObjMediaInfo obj :this.texteMedia){
		if(obj.compareTo(mediaobject)==0)
			return true;
	}
	return false;
}

public boolean imageArrayContainMediaObject(ObjMediaInfo mediaobject){
	for(ObjMediaInfo obj :this.pictureMedia){
		if(obj.compareTo(mediaobject)==0)
			return true;
	}
	return false;
}

public boolean sonArrayContainMediaObject(ObjMediaInfo mediaobject){
	for(ObjMediaInfo obj :this.audioMedia){
		if(obj.compareTo(mediaobject)==0)
			return true;
	}
	return false;
}

public void setVideoMedia(ArrayList<ObjMediaInfo> videoMedia) {
	this.videoMedia = videoMedia;
}

public ArrayList<ObjMediaInfo> getAudioMedia() {
	return audioMedia;
}

public void setAudioMedia(ArrayList<ObjMediaInfo> audioMedia) {
	this.audioMedia = audioMedia;
}

public ArrayList<ObjMediaInfo> getPictureMedia() {
	return pictureMedia;
}

public void setPictureMedia(ArrayList<ObjMediaInfo> pictureMedia) {
	this.pictureMedia = pictureMedia;
}

public ArrayList<ObjMediaInfo> getTexteMedia() {
	return texteMedia;
}

public void setTexteMedia(ArrayList<ObjMediaInfo> texteMedia) {
	this.texteMedia = texteMedia;
}

public ObjMediaInfo getSelectedObjMediaInfo() {
	return selectedObjMediaInfo;
}

public void setSelectedObjMediaInfo(ObjMediaInfo selectedObjMediaInfo) {
	this.selectedObjMediaInfo = selectedObjMediaInfo;
}

public String getURL() {
	return URL;
}



}


