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
	

	@Override
	public void onReceive(Context context, Intent intent)
	{	
    
		String action = intent.getAction();

		Log.e("INFO : ", "Broadcast Receive Start");
		if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION ) || action.equals(ConnectivityManager.TYPE_MOBILE)) 
		{
			Log.v(tag, "its cool");
		    NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
		    if(networkInfo.isConnected())
		    {
		    	Log.e("INFO : ", "Broadcast Receive");
				Toast.makeText(context, "Réseau internet disponible", 3).show();
				Intent serviceIntent = new Intent(context, DMService.class);
				context.startService(serviceIntent);
		    }
		}
		else if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) 
		{
		    NetworkInfo networkInfo =intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		    if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI || networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET  && ! networkInfo.isConnected()) 
		    {

		        // Wifi is disconnected
		    	Toast.makeText(context,
						"Réseau non disponible, veuillez vérifier votre connexion internet", 3).show();
		    }
		}
	}
}
