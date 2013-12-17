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
import com.example.androidtp.view.ImageCustomAdapter;

public class ImageFragment extends ListFragment implements Observer
{

	/**
	 * ListFragment class with Observer
	 */

	private ImageCustomAdapter imageCustomAdapter;
	private MenuItem mRefresh = null;
	private GlobalMethods application;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
		View view = inflater.inflate(
				getResources().getLayout(R.layout.imagefragment_layout),
				container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();
		imageCustomAdapter = new ImageCustomAdapter(getActivity()
				.getApplicationContext(), MediaManager.getInstance()
				.getPictureMedia());
		setListAdapter(imageCustomAdapter);

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
		// TODO Auto-generated method stub
		if (mRefresh != null)
		{
			MenuItemCompat.setActionView(mRefresh, null);
		}
		imageCustomAdapter.notifyDataSetChanged();
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
