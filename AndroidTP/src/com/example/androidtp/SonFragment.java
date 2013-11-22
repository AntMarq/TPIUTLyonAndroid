package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import com.example.androidtp.model.MediaManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SonFragment extends ListFragment implements Observer
{
	private SonCustomAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate (getResources ().getLayout (R.layout.sonfragment_layout), container, false);
		adapter = new SonCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getAudioMedia());
		setListAdapter (adapter);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) 
	{
		Log.v("SonFragment", "updatingadapterson");
		
		
		
	}
}
