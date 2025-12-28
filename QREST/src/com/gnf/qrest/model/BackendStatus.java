package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

//public class BackendStatus extends QResponse {
public class BackendStatus {

	public boolean state;
	public String status;
	public String message;

	@JsonProperty("length_queue")
	public int lengthQueue;
	
	@JsonProperty("backend_version")
	public String backendVersion;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLengthQueue() {
		return lengthQueue;
	}

	public void setLengthQueue(int lengthQueue) {
		this.lengthQueue = lengthQueue;
	}

	public String getBackendVersion() {
		return backendVersion;
	}

	public void setBackendVersion(String backendVersion) {
		this.backendVersion = backendVersion;
	}
	
	
}
