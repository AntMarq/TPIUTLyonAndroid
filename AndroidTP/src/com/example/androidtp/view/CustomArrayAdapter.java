package com.example.androidtp.view;

import java.util.ArrayList;

import com.example.androidtp.R;
import com.example.androidtp.R.drawable;
import com.example.androidtp.R.id;
import com.example.androidtp.R.layout;
import com.example.tpandroid.model.CategorieDrawer;
import com.example.tpandroid.model.ObjDrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomArrayAdapter extends BaseAdapter
{


    private  Context mContext;
    LayoutInflater inflater ;
    ArrayList<Object> listObjDrawer;
	private static final int 	 				 TYPE_ITEM = 0;
	private static final int 	 				 TYPE_SEPARATOR = 1;
	private static final int 	 			 	 TYPE_MAX_COUNT = 2;
	String tag = "CustomArrayAdapter";
	ViewHolder holder = null;
	public CustomArrayAdapter(Context context, ArrayList<Object> objDrawer) 
	{
		inflater = LayoutInflater.from(context);
		this.mContext = context;
		this.listObjDrawer = objDrawer;
	}
	
	 @Override
     public int getItemViewType(int position)
	 {
		 if (listObjDrawer.get (position) instanceof ObjDrawer)
		 {
         return  TYPE_ITEM;
		 }
		 return TYPE_SEPARATOR;
     }
	 
	
	 
	 @Override
	    public int getViewTypeCount() 
		{
	        return TYPE_MAX_COUNT;
	    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) 
	{	
		int type = getItemViewType(position);
		if (convertView == null)
		{
				holder = new ViewHolder();
				
				switch(type)
				{
					case TYPE_ITEM:
       
						convertView = inflater.inflate(R.layout.select_item, null);
						holder.titre = (TextView)convertView.findViewById (R.id.item_title);
						holder.image = (ImageView)convertView.findViewById (R.id.item_icon);
						break;
					case TYPE_SEPARATOR:
						convertView = inflater.inflate(R.layout.liste_fragment_categ, null);
						holder.categDrawer = (TextView)convertView.findViewById (R.id.nomCateg);
						break;	
				}
				convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
				switch(type)
				{
					
					case TYPE_ITEM:
						
						
						holder.titre.setText((((ObjDrawer)listObjDrawer.get(position))).getTitle());
						
						if(holder.titre.getText().equals("Video"))
						{
							holder.image.setImageResource(R.drawable.movie);
						}
						else if (holder.titre.getText().equals("Son"))
						{
							holder.image.setImageResource(R.drawable.sound);
						}
						else if (holder.titre.getText().equals("Image"))
						{
							holder.image.setImageResource(R.drawable.picture);
						}
						else
						{
						holder.image.setImageResource(R.drawable.text);
						}
						
				
						break;	
						
					case TYPE_SEPARATOR:
						holder.categDrawer.setText((((CategorieDrawer) listObjDrawer.get(position))).getCateg().toString());
						break;	
				}
	
				return convertView;
	}
	
	 private class ViewHolder 
		{
			TextView 	categDrawer;
			TextView	titre;
			ImageView	image;
			
		}

	@Override
	public int getCount ()
	{
		return listObjDrawer.size ();
	}

	@Override
	public Object getItem (int position)
	{

		return listObjDrawer.get (position);
	}

	@Override
	public long getItemId (int position)
	{
		
		return position;
	}

	
}