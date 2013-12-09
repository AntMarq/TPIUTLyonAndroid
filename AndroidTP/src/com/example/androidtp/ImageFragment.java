package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.view.ImageCustomAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ImageFragment extends ListFragment implements Observer
{
	private ImageCustomAdapter imageCustomAdapter;
	private MenuItem mRefresh = null;
	private GlobalMethods application;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		setHasOptionsMenu(true);
		View view = inflater.inflate (getResources ().getLayout (R.layout.imagefragment_layout), container, false);
		application = (GlobalMethods) getActivity ().getApplicationContext ();
		imageCustomAdapter = new ImageCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getPictureMedia());
		setListAdapter (imageCustomAdapter);

		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) 
	{
		// TODO Auto-generated method stub
		if(mRefresh != null)
		{			
			 MenuItemCompat.setActionView(mRefresh,null);
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
			 case R.id.refresh:	
		     mRefresh = item;
			 MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
			 application.refreshOnline();
			 break;        
		}
			return false;
	}

}
