package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Backend {
	private String name;
	private String state;
	private String message;

	@JsonProperty("is_simulator")
	private boolean isSimulator;

	private int qubits;
	
	@JsonProperty("length_queue")
	private int queueLength;
	
	@JsonProperty("backend_version")
	private String backendVersion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public String getBackendVersion() {
		return backendVersion;
	}

	public void setBackendVersion(String backendVersion) {
		this.backendVersion = backendVersion;
	}

	public boolean isSimulator() {
		return isSimulator;
	}

	public void setSimulator(boolean isSimulator) {
		this.isSimulator = isSimulator;
	}

	public int getQubits() {
		return qubits;
	}

	public void setQubits(int qubits) {
		this.qubits = qubits;
	}

	
}
