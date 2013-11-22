package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.view.ImageCustomAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImageFragment extends ListFragment implements Observer
{
	private ImageCustomAdapter imageCustomAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate (getResources ().getLayout (R.layout.imagefragment_layout), container, false);
		imageCustomAdapter = new ImageCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getPictureMedia());
		setListAdapter (imageCustomAdapter);

		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
