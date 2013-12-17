package com.example.androidtp;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.androidtp.model.CategorieDrawer;
import com.example.androidtp.model.ObjDrawer;

import android.content.Context;

public class ArrayDrawerData extends ArrayList<Object>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Context context;
	String[] menuTitles;
	String[] menuCateg;
	String tag = "ArrayDrawerData";

	public ArrayDrawerData(Context context)
	{
		super();
		this.context = context;
		loadDrawer();

	}

	/**
	 * Organize Category and Item for the navigation drawer
	 */
	public void loadDrawer()
	{
		menuTitles = this.context.getResources().getStringArray(
				R.array.actu_array);
		menuCateg = this.context.getResources().getStringArray(
				R.array.categ_array);

		for (int i = 0; i < menuCateg.length; i++)
		{
			/**
			 * First Category
			 */
			if (i == 0)
			{
				CategorieDrawer newCateg = new CategorieDrawer();
				newCateg.setCateg(menuCateg[i]);
				this.add(newCateg);
				/**
				 * Item for first category
				 */
				for (int j = 0; j < menuTitles.length; j++)
				{
					ObjDrawer newObjDrawer = new ObjDrawer();
					newObjDrawer.setTitle(menuTitles[j]);
					this.add(newObjDrawer);
				}
			} else
			{
				ObjDrawer newObjDrawer = new ObjDrawer();
				newObjDrawer.setTitle(menuTitles[2]);
				this.add(newObjDrawer);
			}
		}
	}

	@Override
	public String toString()
	{
		return "ArrayDrawerData [context=" + context + ", menuTitles="
				+ Arrays.toString(menuTitles) + ", menuCateg="
				+ Arrays.toString(menuCateg) + "]";
	}
}
