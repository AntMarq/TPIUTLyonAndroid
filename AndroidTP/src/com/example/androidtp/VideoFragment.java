package com.example.androidtp;


import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtp.model.MediaManager;

public class VideoFragment extends ListFragment implements Observer

{
	private CustomAdapter adapter;
	String tag = "VideoFRagment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(tag, "onCreateView");

		View view = inflater.inflate(
				getResources().getLayout(R.layout.videofragment_layout),
				container, false);

		// MediaLoaderAsync_task.setOnXMLLoadFinishedListener(this);

		adapter = new CustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getLocalType("video"));
		setListAdapter(adapter);

		MediaManager.getInstance().addObserver(this);
		setListAdapter (adapter);

		return view;
	}

	public CustomAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(CustomAdapter adapter) 
	{
		this.adapter = adapter;
	}


	@Override
	public void update(Observable observable, Object data) {
		Log.v("videoFragent", "updatingadapter");
		
		  adapter = new
		 CustomAdapter(getActivity().getApplicationContext(),MediaManager
		 .getInstance().getLocalType("video")); setListAdapter (adapter);
		 
		this.adapter.notifyDataSetChanged();

	}
}
