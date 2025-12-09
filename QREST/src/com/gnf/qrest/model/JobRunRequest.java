package com.gnf.qrest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRunRequest {
	@JsonProperty("program_id")
	private String programId;
	private Params params;
	private String backend;
	
	public JobRunRequest(String backend,String qasm, List<String> observables) {
		this(backend,qasm,observables,new HashMap());
	}

	public JobRunRequest(String backend,String qasm, List<String> observables, Map<String,Object> pp) {
		this.backend = backend;
		this.programId = "estimator";
		this.params = new Params();
		
		params.addPub(new EstimatorPUB(qasm,observables,pp));
		
	}

	public JobRunRequest(String backend,String qasm) {
		this(backend,qasm,new HashMap());
		
//		params.addPub(Arrays.asList(qasm,new HashMap(),256));
//		params.addPub(new SamplerPUB(qasm,pp,256));
		
	}

	public JobRunRequest(String backend,String qasm, Map<String,Object> pp) {
		this.backend = backend;
		this.programId = "sampler";
		this.params = new Params();
		
//		params.addPub(Arrays.asList(qasm,new HashMap(),256));
		params.addPub(new SamplerPUB(qasm,pp,256));
		
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	
}
