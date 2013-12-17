package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.view.SonCustomAdapter;

public class SonFragment extends ListFragment implements Observer
{
	private SonCustomAdapter adapter;
	private MenuItem mRefresh = null;
	private GlobalMethods application;

	/**
	 * ListFragment class with Observer
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
		View view = inflater.inflate(
				getResources().getLayout(R.layout.sonfragment_layout),
				container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();
		adapter = new SonCustomAdapter(getActivity().getApplicationContext(),
				MediaManager.getInstance().getAudioMedia());
		setListAdapter(adapter);
		MediaManager.getInstance().addObserver(this);
		return view;
	}

	/**
	 * Update View when the first loading is finish (first run) Update View when
	 * the user click on refresh button
	 * 
	 */

	@Override
	public void update(Observable observable, Object data)
	{
		if (mRefresh != null)
		{
			MenuItemCompat.setActionView(mRefresh, null);
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		inflater.inflate(R.menu.fragment_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.refresh :
				mRefresh = item;
				if (application.isOnline(getActivity().getApplicationContext()) == true)
				{
					MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
					application.refreshOnline();
				} else
				{
					Toast.makeText(application.getBaseContext(),
							"Veuillez activer votre connexion internet", 3).show();
				}

				break;
		}
		return false;
	}
}
