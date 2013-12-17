package com.example.androidtp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DMService extends Service
{
	private String tag = "DMService" ;
	boolean mIsRunning = false;

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.v(tag, "DMService");
		if (!mIsRunning)
		{
			Log.e("INFO : ", "SERVICE STARTED");
			mIsRunning = true;
		}
		GlobalMethods application = (GlobalMethods) getApplicationContext();
		application.refreshOnline();
		return START_STICKY;
	}

}
