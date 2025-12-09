package com.gnf.qrest.model;

import java.util.ArrayList;
import java.util.List;

public class Params {
//	private List<List<Object>> pubs;
	private List<Object> pubs;
	private Options options;
	private int version = 2;
	
	public Params() {
		
	}

	public Options getOptions() {
		if (options == null) {
			options = new Options();
		}
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

//	public List<List<Object>> getPubs() {
	public List<Object> getPubs() {
		if (pubs==null) {
			pubs = new ArrayList<Object>();
		}
		return pubs;
	}

//	public void setPubs(List<List<Object>> pubs) {
	public void setPubs(List<Object> pubs) {
		this.pubs = pubs;
	}

//	public void addPub(List<Object> pub) {
//		getPubs().add(pub);
//	}

	public void addPub(PUB pub) {
		getPubs().add(pub.toList());
	}
	
}
