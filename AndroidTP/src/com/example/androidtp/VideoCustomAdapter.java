package com.example.androidtp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtp.model.ObjMediaInfo;

public class VideoCustomAdapter extends BaseAdapter 
{
	
/**
 * Adapter for VideoFragment
 */
	private Context 			 mContext;
	private ArrayList<ObjMediaInfo> newList ;
	private LayoutInflater 		 inflater;
	
	public VideoCustomAdapter(Context context,ArrayList<ObjMediaInfo> arrayList) 
	{		
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.newList = arrayList ;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newList.size();
	}

	@Override
	public Object getItem(int position) {
		return newList.get (position);
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
				
				convertView = inflater.inflate(R.layout.video_cell, null);
				
				holder.titleVideo = (TextView)convertView.findViewById (R.id.titre);
				holder.imageVideo = (ImageView)convertView.findViewById (R.id.image);
				holder.pathVideo = (TextView)convertView.findViewById (R.id.textpath);
	}						
	else 
	{
		holder = (ViewHolder)convertView.getTag();				
	}
		
		final ObjMediaInfo objMedia = ((ObjMediaInfo)(newList.get(position)));
		holder.titleVideo.setText (objMedia.get_name());
		holder.imageVideo.setImageResource(R.drawable.icon_lyon);
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
