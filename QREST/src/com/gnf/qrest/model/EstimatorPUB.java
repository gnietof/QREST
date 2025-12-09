package com.gnf.qrest.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstimatorPUB extends PUB {

	private List<String> observables;
	private Double precision;
	
	public EstimatorPUB(String circuit, List<String> observables) {
		this(circuit,observables,new HashMap<String,Object>());
	}
	
	public EstimatorPUB(String circuit, List<String> observables,Map<String, Object> parameters) {
		this(circuit,observables,parameters,0.015625);
	}

	public EstimatorPUB(String circuit, List<String> observables, Map<String, Object> parameters, double precision) {
		super(circuit,parameters);
//		this.circuit = circuit;
//		this.parameters = parameters;
		this.observables = observables;
		this.precision = precision;
	}
	
	public Object toList() {
		return List.of(getCircuit(),observables,getParameters(),precision);
//		if (shots!=null) {
//			return List.of(circuit,parameters,shots);
//		} else if (parameters!=null) {
//			return List.of(circuit,parameters);
//		} else {
//			return List.of(circuit);
//		}
	}

//	@Override
//	public String getCircuit() {
//		return circuit;
//	}
//
//	@Override
//	public void setCircuit(String circuit) {
//		this.circuit = circuit;
//	}
//
//	@Override
//	public Map<String, Object> getParameters() {
//		return parameters;
//	}
//
//	@Override
//	public void setParameters(Map<String, Object> parameters) {
//		this.parameters = parameters;
//	}

	public List<String> getObservables() {
		return observables;
	}

	public void setObservables(List<String> observables) {
		this.observables = observables;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	
}
