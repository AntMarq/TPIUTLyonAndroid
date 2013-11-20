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
	private VideoCustomAdapter adapter;
	String tag = "VideoFRagment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(tag, "onCreateView");

		View view = inflater.inflate(
				getResources().getLayout(R.layout.videofragment_layout),
				container, false);

		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		Log.v("videoFragent", "updatingadaptervideo");
		
		adapter = new VideoCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getLocalType("video"));
		setListAdapter (adapter);

	}
}
