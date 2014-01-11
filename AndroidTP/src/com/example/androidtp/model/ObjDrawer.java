package com.example.androidtp.model;

public class ObjDrawer
{

	private int icon;
	private String title;
	private int counter;

	public ObjDrawer()
	{
		super();
	}

	public int getIcon()
	{
		return icon;
	}
	public void setIcon(int icon)
	{
		this.icon = icon;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "ObjDrawer [icon=" + icon + ", title=" + title + ", counter="
				+ counter + "]";
	}
	

}