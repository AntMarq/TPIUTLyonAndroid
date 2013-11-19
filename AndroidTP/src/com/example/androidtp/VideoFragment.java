package com.example.androidtp;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidtp.model.MediaManager;

public class VideoFragment extends ListFragment 
{
	
	private Handler		listeHandler;
	private CustomAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		View view = inflater.inflate (getResources ().getLayout (R.layout.videofragment_layout), container, false);
		
		listeHandler = new Handler ()
		{				
				public void handleMessage (Message msg)
				{
					int done = msg.arg1;
					if (done == 0)
					{
						getActivity().setProgressBarIndeterminateVisibility(false);
						Toast.makeText (getActivity ().getApplicationContext (), "Une erreur est survenue",Toast.LENGTH_SHORT).show ();
					}
					else
					{							
						getActivity().setProgressBarIndeterminateVisibility(false);
						adapter.notifyDataSetChanged ();
						
					}
				}
		};
		
		adapter = new CustomAdapter(getActivity().getApplicationContext(),MediaManager.getInstance().getLocalType("video"));
		setListAdapter (adapter);

		return view;
	}
	
	

}
