package com.example.androidtp;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class VideoFragment extends ListFragment 
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate (getResources ().getLayout (R.layout.videofragment_layout), container, false);
		
		
		
		
		
		return view;
	}
	
	@Override
	public void onListItemClick (ListView listView, View view, int position, long id)
	{
		
	}
	

}
