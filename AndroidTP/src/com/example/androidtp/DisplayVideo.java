package com.example.androidtp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



public class DisplayVideo extends FragmentActivity
	{
		static final String NAME = "DisplayVideo";

		@Override
		public void onCreate (Bundle savedInstanceState)
		{
			super.onCreate (savedInstanceState);
			setContentView (R.layout.displayvideo);
			
			Intent getAbTitle= getIntent();
			Bundle b = getAbTitle.getExtras();
			if(b!=null)
		    {
		        String j =(String) b.get("title");
		        getActionBar().setTitle(j);
		    }	
			getActionBar().setDisplayHomeAsUpEnabled(true);
			getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);		
		}
}
