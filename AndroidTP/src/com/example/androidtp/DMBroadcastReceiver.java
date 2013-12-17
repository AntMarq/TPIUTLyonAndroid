package com.example.androidtp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lionelbanand on 15/12/13.
 */
public class DMBroadcastReceiver extends BroadcastReceiver
{

	private String tag = "DMBroadcastReceiver";
	

	@Override
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();
		if (action != null && action.equals("android.net.conn.CONNECTIVITY_CHANGE"))
		{
			Log.e("INFO : ", "Broadcast Receive");
			Intent serviceIntent = new Intent(context, DMService.class);
			context.startService(serviceIntent);
		}
		
		GlobalMethods application = (GlobalMethods)context.getApplicationContext();
		
		if (application.isOnline(context) == true)
		{
			
			Toast.makeText(application.getBaseContext(), "Réseau internet disponible", 3).show();
		} else
		{
			Toast.makeText(application.getBaseContext(),
					"Réseau non disponible, veuillez vérifier votre connexion internet", 3).show();
		}
	}
}
