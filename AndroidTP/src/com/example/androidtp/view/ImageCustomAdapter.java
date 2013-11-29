package com.example.androidtp.view;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtp.R;
import com.example.androidtp.R.drawable;
import com.example.androidtp.R.id;
import com.example.androidtp.R.layout;
import com.example.androidtp.model.ObjMediaInfo;

public class ImageCustomAdapter extends BaseAdapter
{
	private Context 			 mContext;
	private ArrayList<ObjMediaInfo> newPictureList ;
	private LayoutInflater 		 inflater;
	
	public ImageCustomAdapter(Context context,ArrayList<ObjMediaInfo> arrayList) 
	{		
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.newPictureList = arrayList ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newPictureList.size();
	}

	@Override
	public Object getItem(int position) {
		return newPictureList.get (position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView (int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
			
		if (convertView == null)
		{
				holder = new ViewHolder();
				
				convertView = inflater.inflate(R.layout.image_cell, null);
				
				holder.titlePicture = (TextView)convertView.findViewById (R.id.titre);
				holder.imagePicture = (ImageView)convertView.findViewById (R.id.image);
				holder.pathPicture = (TextView)convertView.findViewById (R.id.textpath);
				convertView.setTag(holder);
	}						
	else 
	{
		holder = (ViewHolder)convertView.getTag();				
	}
		
		final ObjMediaInfo objMedia = ((ObjMediaInfo)(newPictureList.get(position)));
		holder.titlePicture.setText (objMedia.get_name());
		holder.imagePicture.setImageResource(R.drawable.picture);
		holder.pathPicture.setText( (objMedia.get_url()));
		
	return convertView;			
}
	

	private class ViewHolder 
	{
		TextView 	titlePicture;
		TextView 	pathPicture;
		ImageView	imagePicture;
				
	}
}
