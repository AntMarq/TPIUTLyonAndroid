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

public class SonCustomAdapter extends BaseAdapter
{
	private Context 			 mContext;
	private ArrayList<ObjMediaInfo> newSongList ;
	private LayoutInflater 		 inflater;
	
	public SonCustomAdapter(Context context,ArrayList<ObjMediaInfo> arrayList) 
	{		
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.newSongList = arrayList ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newSongList.size();
	}

	@Override
	public Object getItem(int position) {
		return newSongList.get (position);
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
				
				convertView = inflater.inflate(R.layout.son_cell, null);
				
				holder.titleVideo = (TextView)convertView.findViewById (R.id.titre);
				holder.imageVideo = (ImageView)convertView.findViewById (R.id.image);
				holder.pathVideo = (TextView)convertView.findViewById (R.id.textpath);
				convertView.setTag(holder);
	}						
	else 
	{
		holder = (ViewHolder)convertView.getTag();				
	}
		
		final ObjMediaInfo objMedia = ((ObjMediaInfo)(newSongList.get(position)));
		holder.titleVideo.setText (objMedia.get_name());
		holder.imageVideo.setImageResource(R.drawable.music_player);
		holder.pathVideo.setText( (objMedia.get_url()));
		
	return convertView;			
}
	

	private class ViewHolder 
	{
		TextView 	titleVideo;
		TextView 	pathVideo;
		ImageView	imageVideo;
				
	}
}
