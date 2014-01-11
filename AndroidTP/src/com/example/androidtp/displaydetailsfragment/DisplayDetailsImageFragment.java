package com.example.androidtp.displaydetailsfragment;

import java.io.File;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtp.GlobalMethods;
import com.example.androidtp.R;
import com.example.androidtp.R.id;
import com.example.androidtp.R.layout;
import com.example.androidtp.loader.DownloadImages;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DisplayDetailsImageFragment extends Fragment
{
	private GlobalMethods application;
	private String tag = "DisplayImageFragment";
	private String advice = "Cliquez sur l'image pour la télécharger";
	private ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.displayimagefragment, container, false);
		application = (GlobalMethods) getActivity().getApplicationContext();

		setHasOptionsMenu(true);

		TextView viewTitre = (TextView) view.findViewById(R.id.titre);
		ImageView image = (ImageView) view.findViewById(R.id.image);
		TextView contentText = (TextView) view.findViewById(R.id.content);

		final ObjMediaInfo newObjDetail = application.getSelectedObjMediaInfo();

		viewTitre.setText("Titre : " + newObjDetail.get_name());	
		contentText.setText(advice);
		
		if(newObjDetail.get_imageView() != null)
		{
			image.setImageBitmap(newObjDetail.get_imageView());
		}
		else
		{
			/**
			 * Load image cache
			 */
			File cachedImage = imageLoader.getDiscCache().get(MediaManager.getInstance().getBaseUrl() + newObjDetail.get_url() );
			if (cachedImage.exists()) 
			{
				String cacheStringFile = cachedImage.toString();
				Bitmap bitmap = BitmapFactory.decodeFile(cacheStringFile);
				image.setImageBitmap(bitmap);
			}
			
		}

		image.setOnClickListener(new View.OnClickListener()
		{
			
			public void onClick(View v)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Telechargement");
				builder.setMessage("Voulez vous telecharger cette image ?");
				builder.setCancelable(true);
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int id)
					{
						Log.v(tag, "onClick");
						dialog.cancel();
					}
				});
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int id)
					{
						String imageName = newObjDetail.get_name();
						String urlImage = MediaManager.getInstance().getBaseUrl() + newObjDetail.get_url();
						String path = MediaManager.getInstance().getDirectorypath();
						new DownloadImages(getActivity()).execute (urlImage,path,imageName);
						
					}
				});
				builder.show();
			}
		});

		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getTitle().toString().contentEquals(getActivity().getActionBar().getTitle()))
		{
			
			//  clic sur l'icone de l'appli donc retour activity precedente
			 
			getActivity().finish();
		}

		return false;
	}
}	