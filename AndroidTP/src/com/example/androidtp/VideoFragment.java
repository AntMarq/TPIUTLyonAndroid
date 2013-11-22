package com.example.androidtp;


import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;


public class VideoFragment extends ListFragment implements Observer
{
	private VideoCustomAdapter adapter;
	String tag = "VideoFRagment";
	private GlobalMethods application;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		Log.v(tag, "onCreateView");

		View view = inflater.inflate(getResources().getLayout(R.layout.videofragment_layout),container, false);
		application = (GlobalMethods) getActivity ().getApplicationContext ();
		adapter = new VideoCustomAdapter(application,MediaManager.getInstance().getVideoMedia());
		setListAdapter (adapter);

		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		Log.v("videoFragent", "updatingadaptervideo" );

		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onListItemClick (ListView listView, View view, int position, long id)
	{
		ObjMediaInfo detailsVideo = (ObjMediaInfo) listView.getItemAtPosition (position);
		VideoFragment detailfrag = (VideoFragment) getFragmentManager ().findFragmentByTag("Video");

		if (detailfrag != null && detailfrag.isInLayout ())
		{
			// mise à jour
			GlobalMethods application = (GlobalMethods) getActivity ().getApplicationContext ();
			application.setSelectedObjMediaInfo(detailsVideo);
			//MediaManager.getInstance().getVideoMedia().get(position);
			
			
		}
		else
		{
			application.setSelectedObjMediaInfo(detailsVideo);
		//	MediaManager.getInstance().getVideoMedia().get(position);
			Intent intent = new Intent (getActivity ().getApplicationContext (), DisplayVideo.class);
			
			String abTitle  = MediaManager.getInstance().getVideoMedia().get(position).get_name();
			
			//Add title in the next fragment actionbar 
			intent.putExtra("title", abTitle);
			startActivity (intent);
		}
	}
}
