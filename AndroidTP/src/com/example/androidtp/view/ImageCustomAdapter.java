package com.example.androidtp.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidtp.R;
import com.example.androidtp.model.MediaManager;
import com.example.androidtp.model.ObjMediaInfo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class ImageCustomAdapter extends BaseAdapter
{
	private Context mContext;
	String tag = "ImageCustomAdapter";
	private ArrayList<ObjMediaInfo> newPictureList;
	private LayoutInflater inflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public ImageCustomAdapter(Context context, ArrayList<ObjMediaInfo> arrayList)
	{
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.newPictureList = arrayList;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return newPictureList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return newPictureList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;

		if (convertView == null)
		{
			holder = new ViewHolder();

			convertView = inflater.inflate(R.layout.image_cell, null);

			holder.titlePicture = (TextView) convertView.findViewById(R.id.titre);
			holder.imagePicture = (ImageView) convertView.findViewById(R.id.image);
			holder.pathPicture = (TextView) convertView.findViewById(R.id.textpath);
			holder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar1);
			convertView.setTag(holder);
		} 
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		final ObjMediaInfo objMedia = ((ObjMediaInfo) (newPictureList.get(position)));
		holder.titlePicture.setText(objMedia.get_name());
		holder.imagePicture.setImageBitmap(objMedia.get_imageView());
		holder.pathPicture.setText((objMedia.get_url()));

		final ViewHolder finalHolder = holder;
	
		if (objMedia.get_url() != null)
		{

			DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true) // default
					.cacheOnDisc(true).build();

			imageLoader.displayImage(MediaManager.getInstance().getBaseUrl() + objMedia.get_url(), holder.imagePicture,
					options, new ImageLoadingListener()
					{
						@Override
						public void onLoadingStarted(String imageUri, View view)
						{
							finalHolder.imagePicture.setImageResource(R.drawable.picture); 
							finalHolder.progressBar.setVisibility(View.VISIBLE);
						}
						@Override
						public void onLoadingFailed(String imageUri, View view, FailReason failReason)
						{
							finalHolder.imagePicture.setImageResource(R.drawable.picture);
							finalHolder.progressBar.setVisibility(View.GONE);

						}
						@Override
						public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
						{
							finalHolder.progressBar.setVisibility(View.GONE);
						}
						@Override
						public void onLoadingCancelled(String imageUri, View view)
						{
							finalHolder.progressBar.setVisibility(View.GONE);
						}

					});
		} else if (objMedia.get_imageView() != null)
		{

			holder.imagePicture.setImageBitmap(objMedia.get_imageView());
			
		} else
		{
			holder.imagePicture.setImageResource(R.drawable.picture);
			
		}

		return convertView;
	}

	private class ViewHolder
	{
		TextView titlePicture;
		TextView pathPicture;
		ImageView imagePicture;
		ProgressBar progressBar;

	}
}
