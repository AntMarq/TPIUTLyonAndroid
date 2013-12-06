package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.example.androidtp.view.TextCustomAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TextFragment extends ListFragment implements Observer
{
	private TextCustomAdapter textCustomAdapter;
	String tag = "TextFragment";
	private GlobalMethods application;
	private MenuItem mRefresh = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		setHasOptionsMenu(true);	
		
		View view = inflater.inflate (getResources ().getLayout (R.layout.textfragment_layout), container, false);
		
		textCustomAdapter = new TextCustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getTexteMedia());
		setListAdapter (textCustomAdapter);
		
		application = (GlobalMethods) getActivity ().getApplicationContext ();
		MediaManager.getInstance().addObserver(this);
		return view;
	}

	@Override
	public void update(Observable observable, Object data) {

		Log.v("textFragent", "updatingadaptertext" );
		if(mRefresh != null)
		{
			
			 MenuItemCompat.setActionView(mRefresh,null);
		}
		textCustomAdapter.notifyDataSetChanged();
		
	}
	
	@Override
	public void onListItemClick (ListView listView, View view, int position, long id)
	{
		ObjMediaInfo detailsText = (ObjMediaInfo) listView.getItemAtPosition (position);
		TextFragment detailfrag = (TextFragment) getFragmentManager ().findFragmentByTag("Text");

		if (detailfrag != null && detailfrag.isInLayout ())
		{
			// mise à jour
			GlobalMethods application = (GlobalMethods) getActivity ().getApplicationContext ();
			application.setSelectedObjMediaInfo(detailsText);
		}
		else
		{
			application.setSelectedObjMediaInfo(detailsText);
			
			//ne marche pas erreure au lancement de DisplayText au moment indiquer par le todo
//			Intent intent = new Intent (getActivity ().getApplicationContext (), DisplayText.class);
//			
//			String abTitle  = MediaManager.getInstance().getTexteMedia().get(position).get_name();
//			
//			//Add title in the next fragment actionbar 
//			intent.putExtra("title", abTitle);
//			startActivity (intent);
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
			 case R.id.refresh:	
		     mRefresh = item;
			 MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
			 application.refreshOnline();
			 break;        
		}
			return false;
	}
}
