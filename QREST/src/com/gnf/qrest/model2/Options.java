package com.gnf.qrest.model2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Options {
	@JsonProperty("default_shots")
	private int defaultShots=16;
	private Execution execution;
	
	public Execution getExecution() {
		return execution;
	} 

	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	public int getDefaultShots() {
		return defaultShots;
	}

	public void setDefaultShots(int defaultShots) {
		this.defaultShots = defaultShots;
	}
}
