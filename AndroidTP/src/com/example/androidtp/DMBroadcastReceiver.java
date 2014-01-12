package com.example.androidtp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;


public class DMBroadcastReceiver extends BroadcastReceiver
{

	private String tag = "DMBroadcastReceiver";
	private ConnectivityManager connectManager ;
	private GlobalMethods application;
	NetworkInfo networkInfo;
	

	@Override
	public void onReceive(Context context, Intent intent)
	{	
    
		String action = intent.getAction();
		networkInfo =intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		Log.e("INFO : ", "Broadcast Receive Start");
		if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) 
		{
			Log.v(tag, "its cool");
		 //   networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		    if(networkInfo.isConnected())
		    {
		    	Log.e("INFO : ", "Broadcast Receive");
				Toast.makeText(context, "Réseau internet disponible", Toast.LENGTH_SHORT).show();
				Intent serviceIntent = new Intent(context, DMService.class);
				context.startService(serviceIntent);
		    }
		    else
		    {
		    	Toast.makeText(context,
						"Réseau non disponible, veuillez vérifier votre connexion internet", Toast.LENGTH_SHORT).show();
		    }
		}
	}
}
