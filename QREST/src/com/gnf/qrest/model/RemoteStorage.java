package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteStorage {

	@JsonProperty("job_params")
    private RemoteStorageObject jobParams;
    private RemoteStorageObject results;
    private RemoteStorageObject logs;
	@JsonProperty("transpiled_circuits")
    private RemoteStorageObject transpiledCircuits;
	
	public RemoteStorageObject getResults() {
		return results;
	}
	
	public void setResults(RemoteStorageObject results) {
		this.results = results;
	}
	
	public RemoteStorageObject getLogs() {
		return logs;
	}
	
	public void setLogs(RemoteStorageObject logs) {
		this.logs = logs;
	}

	public RemoteStorageObject getJobParams() {
		return jobParams;
	}

	public void setJobParams(RemoteStorageObject jobParams) {
		this.jobParams = jobParams;
	}

	public RemoteStorageObject getTranspiledCircuits() {
		return transpiledCircuits;
	}

	public void setTranspiledCircuits(RemoteStorageObject transpiledCircuits) {
		this.transpiledCircuits = transpiledCircuits;
	}
	
}



