package com.example.androidtp;

import com.example.androidtp.model.ObjMediaInfo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayTextFragment extends Fragment{


	GlobalMethods application;
	String tag = "DisplayTextFragment";
	
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate (R.layout.displaytextfragment, container, false);
		application = (GlobalMethods) getActivity ().getApplicationContext ();
		setHasOptionsMenu(true);
		
		TextView viewTitre = (TextView) view.findViewById (R.id.titre);
		TextView viewPath = (TextView) view.findViewById (R.id.path);
		ImageView image = (ImageView) view.findViewById (R.id.imageText);
		TextView contentText = (TextView) view.findViewById (R.id.contenttext);
		
		ObjMediaInfo newObjDetail = application.getSelectedObjMediaInfo();
	
		Log.v(tag, "objdetail = " +  newObjDetail);
		viewTitre.setText(newObjDetail.get_name());
		viewPath.setText(newObjDetail.get_url());
		image.setImageResource(R.drawable.text);
		contentText.setText(newObjDetail.get_content());
		Log.v(tag, "DisplayTextFragment" + newObjDetail.get_content());
		
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
