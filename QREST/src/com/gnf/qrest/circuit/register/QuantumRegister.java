package com.gnf.qrest.circuit;

public class QuantumRegister extends Register {

	public QuantumRegister(int qubits) {
		super(qubits);
	}

	public QuantumRegister(int qubits,String name) {
		super(qubits,name);
	}

	public int getQubits() {
		return getXBits();
	}

	public void setQubits(int qubits) {
		this.setXBits(qubits);
	}

	@Override
	protected String getPrefix() {
		return "q";
	}
	
}
