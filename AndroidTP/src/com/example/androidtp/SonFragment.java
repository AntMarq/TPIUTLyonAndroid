package com.example.androidtp;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.example.androidtp.view.SonCustomAdapter;

public class SonFragment extends ListFragment implements Observer
{
	private SonCustomAdapter adapter;
	private MenuItem mRefresh = null;
	private GlobalMethods application;

	/**
	 * ListFragment class with Observer
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);
		View view = inflater.inflate(getResources().getLayout(R.layout.sonfragment_layout), container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();
		adapter = new SonCustomAdapter(getActivity().getApplicationContext(), MediaManager.getInstance().getAudioMedia());
		setListAdapter(adapter);
		MediaManager.getInstance().addObserver(this);
		return view;
	}

	/**
	 * Update View when the first loading is finish (first run) Update View when
	 * the user click on refresh button
	 * 
	 */

	@Override
	public void update(Observable observable, Object data)
	{
		if (mRefresh != null)
		{
			MenuItemCompat.setActionView(mRefresh, null);
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
		ObjMediaInfo detailsSon = (ObjMediaInfo) listView.getItemAtPosition(position);
		SonFragment detailfrag = (SonFragment) getFragmentManager().findFragmentByTag("Son");

		if (detailfrag != null && detailfrag.isInLayout())
		{
			// mise à jour
			GlobalMethods application = (GlobalMethods) getActivity().getApplicationContext();
			application.setSelectedObjMediaInfo(detailsSon);
		}
		else
		{
			application.setSelectedObjMediaInfo(detailsSon);
			Intent intent = new Intent(getActivity().getApplicationContext(), DisplaySon.class);
			String abTitle = MediaManager.getInstance().getAudioMedia().get(position).get_name();
			/**
			 * Add title in the next fragment actionbar
			 */
			intent.putExtra("title", abTitle);
			startActivity(intent);
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
			case R.id.refresh :
				mRefresh = item;
				if (application.isOnline(getActivity().getApplicationContext()) == true)
				{
					MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
					application.refreshOnline();
				}
				else
				{
					Toast.makeText(application.getBaseContext(), "Veuillez activer votre connexion internet", 3).show();
				}

				break;
		}
		return false;
	}
}
