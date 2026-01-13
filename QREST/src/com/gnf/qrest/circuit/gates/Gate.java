package com.gnf.qrest.circuit.gates;

public abstract class Gate {

	private String name;

	private int qubits;
	private int clbits;
	
	public abstract String dump();
}
