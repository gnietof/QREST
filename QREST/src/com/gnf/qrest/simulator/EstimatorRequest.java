package com.gnf.qrest.simulator;

public class EstimatorRequest {

	private String circuit;
	private String observable;
	private int shots;
	
	public String getCircuit() {
		return circuit;
	}

	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public String getObservable() {
		return observable;
	}

	public void setObservable(String observable) {
		this.observable = observable;
	}
	
}
