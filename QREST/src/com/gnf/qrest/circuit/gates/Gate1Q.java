package com.gnf.qrest.circuit.gates;

public abstract class Gate1Q extends Gate {

	private int qubit;
	
	public Gate1Q(int qubit) {
		this.qubit = qubit;
	}

	public int getQubit() {
		return qubit;
	}

	public void setQubit(int qubit) {
		this.qubit = qubit;
	}
	
}
