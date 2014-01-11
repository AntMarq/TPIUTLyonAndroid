package com.example.androidtp.loader;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.example.androidtp.GlobalMethods;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class DownloadImages extends AsyncTask<String, Integer, String>
{
	private Context context;
	private String tag = "DownloadImages";
	ProgressDialog dialog;
	
	public DownloadImages(FragmentActivity context)
	{
		super();
		this.context = context;
	}
	
	@Override
	protected void onPreExecute()
	{		
		 dialog= new ProgressDialog(context);
         dialog.setCancelable(false);
         dialog.setIndeterminate(false);
         dialog.setMessage("Downloading! Please wait...!");
         dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
         dialog.show();    					   		
	};

	@Override
	protected String doInBackground(String... params)
	{
		//Fonction de telechargement de l'image
		Log.v(tag, "telechargement de l'image");
		try
		{
			URL urlimage = new URL(params[0]);
			
			URLConnection conection = urlimage.openConnection();
			conection.connect();
			int lenghtOfFile = conection.getContentLength();
		
			int count;

			InputStream is = new BufferedInputStream(urlimage.openStream());

			OutputStream output = new FileOutputStream(params[1] + params[2] + ".png");
	
			byte data[] = new byte[1024];
			long total = 0;

			while ((count = is.read(data)) != -1)
			{
				total += count;
				int test = (int) ((total * 100) / lenghtOfFile);
				publishProgress(test);
				output.write(data, 0, count);
			}
			output.flush();
			output.close();
            is.close();
			
		}
		catch (Exception e)
		{
			Log.e("ERROR DOWNLOADING", "Unable to download" + e.getMessage());
		}
		
		return null;
	}
	
	@Override
    protected void onProgressUpdate(Integer...progress) 
	{
        super.onProgressUpdate(progress);
        // Update the ProgressBar
        Log.v(tag,"values"+progress[0]);
        this.dialog.setProgress(progress[0]);
    }

	@Override
	protected void onPostExecute(String result)
	{
		super.onPostExecute(result);
	    dialog.dismiss();
	}
	
	
	
}
