package com.gnf.qrest.model2;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.builders.PUB;

public class Params {
	private List<Object> pubs;
	private Options options;
	private int version = 2;
	
	@JsonProperty("support_qiskit")
	private boolean supportQiskit = false;
	
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

	public List<Object> getPubs() {
		if (pubs==null) {
			pubs = new ArrayList<Object>();
		}
		return pubs;
	}

	public void setPubs(List<Object> pubs) {
		this.pubs = pubs;
	}

	public void addPub(PUB pub) {
		getPubs().add(pub.buildAsList());
	}

	public boolean isSupportQiskit() {
		return supportQiskit;
	}

	public void setSupportQiskit(boolean supportQiskit) {
		this.supportQiskit = supportQiskit;
	}
	
}
