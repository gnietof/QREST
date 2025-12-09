package com.gnf.qrest.model;

import java.util.Map;

public abstract class PUB {
	private String circuit;
	private Map<String, Object> parameters;

	public PUB(String circuit, Map<String, Object> parameters) {
		this.circuit = circuit;
		this.parameters = parameters;
	}

	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public abstract Object toList();
}
