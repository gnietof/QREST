package com.gnf.qrest.model;

import java.util.Collections;
import java.util.List;

public abstract class PUB {

	private String circuit;
	private List<List<Double>> parameters;

	public PUB(Builder<?> builder) {
		this.circuit = builder.circuit;
		this.parameters = builder.parameters;
	}

	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public List<List<Double>> getParameters() {
		return parameters;
	}

	public void setParameters(List<List<Double>> parameters) {
		this.parameters = parameters;
	}
	
	abstract static class Builder<T extends Builder<T>>  {
		private String circuit;
		private List<List<Double>> parameters = Collections.emptyList();
		
		public T circuit(String circuit) {
			this.circuit = circuit;
			return self();
		}
		
		public T parameters(List<List<Double>> parameters) {
			this.parameters = parameters;
			return self();
		}
		
		protected abstract T self();
		
		abstract PUB build();
		
	}
	
}
