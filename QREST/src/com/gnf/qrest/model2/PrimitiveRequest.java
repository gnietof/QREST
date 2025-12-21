package com.gnf.qrest.model2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.builders.PUB;

public abstract class PrimitiveRequest {
	@JsonProperty("program_id")
	private String programId;
	private Params params;
	private String backend;
	
//	@JsonProperty("max_execution_time")
//	private Integer maxExecutionTime=2;

	public PrimitiveRequest(String backend,PUB pub,String programId) {
		this.backend = backend;
		this.programId = programId;
		this.params = new Params();
		params.addPub(pub);
		
	}
	

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}


//	public Integer getMaxExecutionTime() {
//		return maxExecutionTime;
//	}
//
//
//	public void setMaxExecutionTime(Integer maxExecutionTime) {
//		this.maxExecutionTime = maxExecutionTime;
//	}

	
}
