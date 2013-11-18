package com.example.tpandroid.model;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.util.Log;

public class MediaLoaderAsync_task extends AsyncTask<String, Integer, String> {

	public MediaLoaderAsync_task() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPreExecute() {

	};

	@Override
	protected void onProgressUpdate(Integer... progress) {

	}



	@Override
	protected String doInBackground(String... params) {

		// Log.v("MediaLoaderAsync_Task","params[0]= "+params[0]);
		try {
			int count = 0;
			URL url = new URL(params[0]);
			URLConnection conection = url.openConnection();
			conection.connect();
			// getting file length
			int lenghtOfFile = conection.getContentLength();

			// input stream to read file - with 8k buffer
			InputStream input = new BufferedInputStream(url.openStream(), 8192);
			
			// Output stream to write file
				OutputStream output = new FileOutputStream(params[1]+params[2]);
			

			byte data[] = new byte[1024];

			long total = 0;

			while ((count = input.read(data)) != -1) {
				total += count;
				// publishing the progress....
				// After this onProgressUpdate will be called
				// publishProgress("" + (int) ((total * 100) / lenghtOfFile));

				// writing data to file
				output.write(data, 0, count);
			}

			// flushing output
			output.flush();

			// closing streams
			output.close();


			input.close();

		} catch (Exception e) {
			// TODO faire une gestion d'erreur
			Log.e("MediaLoaderAsync_task", e.getMessage());
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		Log.v("MediaoaderAsyncTask","success !");
	}
}
