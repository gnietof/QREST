package com.gnf.qrest.circuit.register;

public abstract class QRegister extends Register {
	
	public QRegister(int qubits) {
		super(qubits);
	}

	public QRegister(int qubits,String name) {
		super(qubits,name);
	}

	public int getQubits() {
		return getXBits();
	}

	public void setQubits(int qubits) {
		setXBits(qubits);;
	}
	
}
