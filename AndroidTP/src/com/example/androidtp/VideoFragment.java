package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.example.androidtp.view.VideoCustomAdapter;

public class VideoFragment extends ListFragment implements Observer
{
	private VideoCustomAdapter adapter;
	String tag = "VideoFRagment";
	private GlobalMethods application;
	private MenuItem mRefresh = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
		View view = inflater.inflate(
				getResources().getLayout(R.layout.videofragment_layout),
				container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();
		adapter = new VideoCustomAdapter(application, MediaManager
				.getInstance().getVideoMedia());
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

	/**
	 * ListItem click for display details item
	 * 
	 */

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id)
	{
		ObjMediaInfo detailsVideo = (ObjMediaInfo) listView
				.getItemAtPosition(position);
		VideoFragment detailfrag = (VideoFragment) getFragmentManager()
				.findFragmentByTag("Video");

		if (detailfrag != null && detailfrag.isInLayout())
		{
			// mise à jour
			GlobalMethods application = (GlobalMethods) getActivity()
					.getApplicationContext();
			application.setSelectedObjMediaInfo(detailsVideo);
		} else
		{
			application.setSelectedObjMediaInfo(detailsVideo);
			Intent intent = new Intent(getActivity().getApplicationContext(),
					DisplayVideo.class);
			String abTitle = MediaManager.getInstance().getVideoMedia()
					.get(position).get_name();
			// Add title in the next fragment actionbar
			intent.putExtra("title", abTitle);
			startActivity(intent);
		}
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
				MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
				application.refreshOnline();
				break;
		}
		return false;
	}
}
