package com.example.androidtp.model;

import java.util.ArrayList;
import java.util.Observable;

import com.example.androidtp.DMBroadcastReceiver;
import com.example.androidtp.loader.MediaLoaderAsync_task;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class MediaManager extends Observable
{

	/**
	 * Singleton
	 */

	private final String FILENAME = "medias.xml";
	private final String URL = "http://lionel.banand.free.fr/lp_iem/updaterLPIEM.php?type=medias&serial=AAA";
	private final String DirectoryPath = Environment.getExternalStorageDirectory() + "/media/androidapp/";
	private final String BaseUrl = "http://lionel.banand.free.fr/lp_iem";
	private final long serialVersionUID = 1L;
	private MediaLoaderAsync_task mediaLoader;
	private static MediaManager mInstance = null;
	private ArrayList<ObjMediaInfo> videoMedia;
	private ArrayList<ObjMediaInfo> audioMedia;
	private ArrayList<ObjMediaInfo> pictureMedia;
	private ArrayList<ObjMediaInfo> texteMedia;
	private ObjMediaInfo selectedObjMediaInfo;
	

	private MediaManager()
	{
		super();
		Log.v("INFO : ", "MediaManager");
		videoMedia = new ArrayList<ObjMediaInfo>();
		audioMedia = new ArrayList<ObjMediaInfo>();
		pictureMedia = new ArrayList<ObjMediaInfo>();
		texteMedia = new ArrayList<ObjMediaInfo>();
	}

	public static MediaManager getInstance()
	{
		if (mInstance == null)
		{
			Log.v("INFO : ", "MediaManager First time");
			mInstance = new MediaManager();
		}

		return mInstance;
	}

	/**
	 * @return the mInstance
	 */
	public static MediaManager getmInstance()
	{
		return mInstance;
	}

	/**
	 * @param mInstance
	 *            the mInstance to set
	 */
	public static void setmInstance(MediaManager mInstance)
	{
		MediaManager.mInstance = mInstance;
	}

	public boolean videoArrayContainMediaObject(ObjMediaInfo mediaobject)
	{
		for (ObjMediaInfo obj : this.getVideoMedia())
		{
			if (obj.compareTo(mediaobject) == 0)
				return true;
		}
		return false;
	}

	public boolean textArrayContainMediaObject(ObjMediaInfo mediaobject)
	{
		for (ObjMediaInfo obj : this.texteMedia)
		{
			if (obj.compareTo(mediaobject) == 0)
				return true;
		}
		return false;
	}

	public boolean imageArrayContainMediaObject(ObjMediaInfo mediaobject)
	{
		for (ObjMediaInfo obj : this.pictureMedia)
		{
			if (obj.compareTo(mediaobject) == 0)
				return true;
		}
		return false;
	}

	public boolean sonArrayContainMediaObject(ObjMediaInfo mediaobject)
	{
		for (ObjMediaInfo obj : this.audioMedia)
		{
			if (obj.compareTo(mediaobject) == 0)
				return true;
		}
		return false;
	}

	/**
	 * @return the directorypath
	 */
	public String getDirectorypath()
	{
		return DirectoryPath;
	}

	public void triggerObservers()
	{
		setChanged();
		notifyObservers();
	}

	public ArrayList<ObjMediaInfo> getVideoMedia()
	{
		return videoMedia;
	}

	public void setVideoMedia(ArrayList<ObjMediaInfo> videoMedia)
	{
		this.videoMedia = videoMedia;
	}

	public ArrayList<ObjMediaInfo> getAudioMedia()
	{
		return audioMedia;
	}

	public void setAudioMedia(ArrayList<ObjMediaInfo> audioMedia)
	{
		this.audioMedia = audioMedia;
	}

	public ArrayList<ObjMediaInfo> getPictureMedia()
	{
		return pictureMedia;
	}

	public void setPictureMedia(ArrayList<ObjMediaInfo> pictureMedia)
	{
		this.pictureMedia = pictureMedia;
	}

	public ArrayList<ObjMediaInfo> getTexteMedia()
	{
		return texteMedia;
	}

	public void setTexteMedia(ArrayList<ObjMediaInfo> texteMedia)
	{
		this.texteMedia = texteMedia;
	}

	public ObjMediaInfo getSelectedObjMediaInfo()
	{
		return selectedObjMediaInfo;
	}

	public void setSelectedObjMediaInfo(ObjMediaInfo selectedObjMediaInfo)
	{
		this.selectedObjMediaInfo = selectedObjMediaInfo;
	}

	public String getURL()
	{
		return URL;
	}

	public String getBaseUrl()
	{
		return BaseUrl;
	}

	public String getFILENAME()
	{
		return FILENAME;
	}

}