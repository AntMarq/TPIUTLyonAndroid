package com.example.androidtp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;


public class DisplayVideoFragment extends Fragment
{
	GlobalMethods application;
	String tag = "DisplayVideoFragment";
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate (R.layout.displayvideofragment, container, false);
		application = (GlobalMethods) getActivity ().getApplicationContext ();
		setHasOptionsMenu(true);
		
		TextView viewTitre = (TextView) view.findViewById (R.id.titre);
		TextView viewPath = (TextView) view.findViewById (R.id.path);
		ImageView image = (ImageView) view.findViewById (R.id.imageVideo);
		
		ObjMediaInfo newObjDetail = application.getSelectedObjMediaInfo();
	
		Log.v(tag, "objdetail = " +  newObjDetail);
		viewTitre.setText(newObjDetail.get_name());
		viewPath.setText(newObjDetail.get_url());
		image.setImageResource(R.drawable.video_player);
		
		return view;
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{

		if(item.getTitle().toString().contentEquals(getActivity().getActionBar().getTitle()))
		{
			//clic sur l'icone de l'appli donc retour activity précédente
			getActivity().finish();
		}
		return false;
	}
	
}	

