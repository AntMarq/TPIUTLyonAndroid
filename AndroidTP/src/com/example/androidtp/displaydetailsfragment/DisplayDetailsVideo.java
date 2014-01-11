package com.example.androidtp.displaydetailsfragment;

import com.example.androidtp.R;
import com.example.androidtp.R.layout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DisplayDetailsVideo extends FragmentActivity
{

	/**
	 * 
	 * @author anthony Add title in actionBar
	 */

	static final String NAME = "DisplayVideo";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayvideo);

		Intent getAbTitle = getIntent();
		Bundle b = getAbTitle.getExtras();
		if (b != null)
		{
			String j = (String) b.get("title");
			getActionBar().setTitle(j);
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	}
}
