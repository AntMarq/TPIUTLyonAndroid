package com.example.androidtp.model;

import android.graphics.Bitmap;
import android.widget.ImageView;



/**
 * @author anthony
 *
 */
public class ObjMediaInfo implements Comparable<ObjMediaInfo>{
	
	private String _url;
	private String _name;
	private String _version;
	private String _content;
	private Boolean _isInstalled = false;
	private String _type;
	private Bitmap _imageView ;
	

	public ObjMediaInfo() {
		super();
	}

	public ObjMediaInfo(String _url, String _name, String _version, String _type) {
		super();
		this._url = _url;
		this._name = _name;
		this._version = _version;
		this._type = _type;
	}

	/**
	 * @return the _url
	 */
	public String get_url() {
		return _url;
	}

	/**
	 * @param _url
	 *            the _url to set
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
	 * @param _name
	 *            the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}

	/**
	 * @return the _description
	 */
	public String get_version() {
		return _version;
	}

	/**
	 * @param _description
	 *            the _description to set
	 */
	public void set_version(String _version) {
		this._version = _version;
	}

	public Boolean get_isInstalled() {
		return _isInstalled;
	}

	public void set_isInstalled(Boolean _isInstalled) {
		this._isInstalled = _isInstalled;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}
	
	public Bitmap get_imageView() {
		return _imageView;
	}

	public void set_imageView(Bitmap _imageView) {
		this._imageView = _imageView;
	}

	@Override
	public int compareTo(ObjMediaInfo another) 
	{
		 if (another.get_version().equals(this.get_version()) && another.get_name().equals(this.get_name()))
				   // c'est le meme
				   return 0;
				  else
				   return -1;
	}

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}
	
	
}
