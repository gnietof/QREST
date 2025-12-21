package com.gnf.qrest.model2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {

	@JsonProperty("quantum_seconds")
	private int quantumSeconds;
	private int seconds;
	
	public int getQuantumSeconds() {
		return quantumSeconds;
	}
	
	public void setQuantumSeconds(int quantumSeconds) {
		this.quantumSeconds = quantumSeconds;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
}
