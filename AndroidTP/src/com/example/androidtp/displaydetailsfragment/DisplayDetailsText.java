package com.example.androidtp.displaydetailsfragment;

import com.example.androidtp.R;
import com.example.androidtp.R.layout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * 
 * @author anthony Add title in actionBar
 */

public class DisplayDetailsText extends FragmentActivity
{
	static final String NAME = "DisplayText";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaytext);

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
