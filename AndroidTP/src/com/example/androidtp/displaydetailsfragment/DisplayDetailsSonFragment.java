package com.example.androidtp.displaydetailsfragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtp.GlobalMethods;
import com.example.androidtp.R;
import com.example.androidtp.R.drawable;
import com.example.androidtp.R.id;
import com.example.androidtp.R.layout;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;

public class DisplayDetailsSonFragment extends Fragment
{
	private GlobalMethods application;
	private String tag = "DisplaySonFragment";
	private String advice = "Pour écouter le son , veuillez cliquer sur l'image" ;

	/**
	 * Show details for item Son
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.displaysonfragment, container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();

		setHasOptionsMenu(true);

		TextView viewTitre = (TextView) view.findViewById(R.id.titre);
		TextView viewPath = (TextView) view.findViewById(R.id.path);
		ImageView image = (ImageView) view.findViewById(R.id.imageSon);
		TextView contentText = (TextView) view.findViewById(R.id.contentson);

		final ObjMediaInfo newObjDetail = application.getSelectedObjMediaInfo();

		viewTitre.setText("Titre : " + newObjDetail.get_name());
		viewPath.setText("Url : " + newObjDetail.get_url());
		image.setImageResource(R.drawable.sound);
		contentText.setText(advice);
		
		image.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) 
		    {
		    	Uri myUri = Uri.parse(MediaManager.getInstance().getBaseUrl() + newObjDetail.get_url());
		    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW); 
		    	intent.setDataAndType(myUri, "audio/*"); 
		    	startActivity(intent);
		    }
		});

		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getTitle().toString()
				.contentEquals(getActivity().getActionBar().getTitle()))
		{
			/**
			 * clic sur l'icone de l'appli donc retour activity precedente
			 */
			getActivity().finish();
		}

		return false;
	}
}
