package com.example.androidtp.model;


public class MediaInfo {
	
	private String _url;
	private String _name;
	private String _version;
	private Boolean _isInstalled=false;


	public MediaInfo(String _url, String _name, String _description) {
		super();
		this._url = _url;
		this._name = _name;
		this._version = _description;
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
	public String get_version() {
		return _version;
	}


	/**
	 * @param _description the _description to set
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


	
	



}
