package com.gnf.qrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class PrimitiveRequest {
	@JsonProperty("program_id")
	private String programId;
	private Params params;
	private String backend;
	
	public PrimitiveRequest(String backend,List<? extends PUB> pubs,String programId) {
		this.backend = backend;
		this.programId = programId;
		this.params = new Params();
		params.setPubs(pubs);
		
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

}
