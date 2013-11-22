package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import com.example.androidtp.model.MediaManager;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TextFragment extends ListFragment implements Observer
{
	private TextCustomAdapter textCustomAdapter;
	String tag = "TextFragment";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate (getResources ().getLayout (R.layout.textfragment_layout), container, false);
		textCustomAdapter = new TextCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getTexteMedia());
		setListAdapter (textCustomAdapter);

		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
