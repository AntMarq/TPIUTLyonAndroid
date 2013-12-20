package com.example.androidtp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import com.nostra13.universalimageloader.core.ImageLoader;

public class DisplayImageFragment extends Fragment
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
		Log.v(tag, "newObjDetail.get_imageView() = "  + newObjDetail.get_imageView());
		
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
						new DownloadImage().execute (urlImage,path,imageName);
						
					}
				});
				builder.show();
			}
		});

		return view;
	}
	
	private class DownloadImage extends AsyncTask<String, Integer, String>
	{
	

		@Override
		protected String doInBackground(String... params)
		{
			//Fonction de telechargement de l'image
			Log.v(tag, "telechargement de l'image");
			try
			{
				URL urlimage = new URL(params[0]);
				
				int count;

				InputStream is = urlimage.openStream();
				
				FileOutputStream fos = new FileOutputStream(params[1] + params[2] + ".png");
				byte data[] = new byte[1024];
				long total = 0;

				while ((count = is.read(data)) != -1)
				{
					total += count;
					fos.write(data, 0, count);
				}
				is.close();
				fos.close();
			}
			catch (Exception e)
			{
				Log.e("ERROR DOWNLOADING", "Unable to download" + e.getMessage());
			}
			return null;
		}
	}	

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getTitle().toString().contentEquals(getActivity().getActionBar().getTitle()))
		{
			/**
			 * clic sur l'icone de l'appli donc retour activity precedente
			 */
			getActivity().finish();
		}

		return false;
	}

}
