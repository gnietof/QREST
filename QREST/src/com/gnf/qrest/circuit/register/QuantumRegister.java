package com.gnf.qrest.circuit.register;

public class QuantumRegister extends QRegister {

	public QuantumRegister(int qubits) {
		super(qubits);
	}

	public QuantumRegister(int qubits,String name) {
		super(qubits,name);
	}

	@Override
	protected String getPrefix() {
		return "q";
	}
	
}
