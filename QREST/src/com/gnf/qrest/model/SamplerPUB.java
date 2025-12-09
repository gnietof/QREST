package com.gnf.qrest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SamplerPUB extends PUB {

//	private String circuit;
//	private Map<String, Object> parameters;
	private Integer shots;
	
	public SamplerPUB(String circuit) {
		this(circuit,new HashMap<String,Object>());
	}
	
	public SamplerPUB(String circuit, Map<String, Object> parameters) {
		this(circuit,parameters,256);
	}

	public SamplerPUB(String circuit, Map<String, Object> parameters, Integer shots) {
		super(circuit,parameters);
//		this.circuit = circuit;
//		this.parameters = parameters;
		this.shots = shots;
	}
	
	public Object toList() {
		return List.of(getCircuit(),getParameters(),shots);
//		if (shots!=null) {
//			return List.of(circuit,parameters,shots);
//		} else if (parameters!=null) {
//			return List.of(circuit,parameters);
//		} else {
//			return List.of(circuit);
//		}
	}

//	public String getCircuit() {
//		return circuit;
//	}
//
//	public void setCircuit(String circuit) {
//		this.circuit = circuit;
//	}
//
//	public Map<String, Object> getParameters() {
//		return parameters;
//	}
//
//	public void setParameters(Map<String, Object> parameters) {
//		this.parameters = parameters;
//	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}
	
}
