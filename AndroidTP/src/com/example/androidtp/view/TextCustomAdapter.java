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
import com.example.androidtp.model.ObjMediaInfo;

public class TextCustomAdapter extends BaseAdapter
{

	/**
	 * Adapter for VideoFragment
	 */
	private Context mContext;
	private ArrayList<ObjMediaInfo> newtTexList;
	private LayoutInflater inflater;

	public TextCustomAdapter(Context context, ArrayList<ObjMediaInfo> arrayList)
	{
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.newtTexList = arrayList;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return newtTexList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return newtTexList.get(position);
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

			convertView = inflater.inflate(R.layout.text_cell, null);

			holder.titleText = (TextView) convertView.findViewById(R.id.titre);
			holder.imageText = (ImageView) convertView.findViewById(R.id.image);
			holder.pathText = (TextView) convertView.findViewById(R.id.textpath);
			holder.content = (TextView) convertView.findViewById(R.id.contenttext);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}

		final ObjMediaInfo objMedia = ((ObjMediaInfo) (newtTexList.get(position)));
		holder.titleText.setText(objMedia.get_name());
		holder.imageText.setImageResource(R.drawable.text);
		holder.pathText.setText(objMedia.get_url());

		return convertView;
	}

	private class ViewHolder
	{
		TextView titleText;
		TextView pathText;
		ImageView imageText;
		TextView content;
	}

}
