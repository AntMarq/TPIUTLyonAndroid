package com.example.tpandroid.model;

import android.graphics.Bitmap;

public class DisplayableFile {
	
	private String _url;
	private String _name;
	private String _description;
	private Bitmap _icon;
	

	public DisplayableFile() {
		// TODO Auto-generated constructor stub
	}


	public DisplayableFile(String _url, String _name, String _description,
			Bitmap _icon) {
		super();
		this._url = _url;
		this._name = _name;
		this._description = _description;
		this._icon = _icon;
	}


	/**
	 * @return the _url
	 */
	public String get_url() {
		return _url;
	}


	/**
	 * @param _url the _url to set
	 */
	public void set_url(String _url) {
		this._url = _url;
	}


	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}


	/**
	 * @param _name the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}


	/**
	 * @return the _description
	 */
	public String get_description() {
		return _description;
	}


	/**
	 * @param _description the _description to set
	 */
	public void set_description(String _description) {
		this._description = _description;
	}


	/**
	 * @return the _icon
	 */
	public Bitmap get_icon() {
		return _icon;
	}


	/**
	 * @param _icon the _icon to set
	 */
	public void set_icon(Bitmap _icon) {
		this._icon = _icon;
	}
	
	



}
