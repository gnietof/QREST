package com.gnf.qrest.transpilation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.model.Paulis;

public class LayoutRequest {

	private String circuit;
	private String backend;
	private List<Paulis> observables;
	
	@JsonProperty("optimization_level")
	private int optimizationLevel=1;

	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}

	public int getOptimizationLevel() {
		return optimizationLevel;
	}

	public void setOptimizationLevel(int optimizationLevel) {
		this.optimizationLevel = optimizationLevel;
	}

	public List<Paulis> getObservables() {
		return observables;
	}

	public void setObservables(List<Paulis> observables) {
		this.observables = observables;
	}
		
}
