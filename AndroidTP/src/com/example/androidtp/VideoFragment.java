package com.example.androidtp;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidtp.model.MediaLoaderAsync_task;
import com.example.androidtp.model.MediaManager;

public class VideoFragment extends ListFragment implements OnXMLLoadFinishedListener
{
	
	private Handler		listeHandler;
	private CustomAdapter adapter;
	String tag = "VideoFRagment";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		Log.v(tag, "onCreateView");
		View view = inflater.inflate (getResources ().getLayout (R.layout.videofragment_layout), container, false);
		
		MediaLoaderAsync_task.setOnXMLLoadFinishedListener(this);
		
		adapter = new CustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getLocalType("video"));		
		setListAdapter (adapter);
		return view;
	}

	public CustomAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(CustomAdapter adapter) {
		this.adapter = adapter;
	}

	@Override
	public void onXMLDataReady(Integer results) {
		// TODO Auto-generated method stub
		
	}

}
