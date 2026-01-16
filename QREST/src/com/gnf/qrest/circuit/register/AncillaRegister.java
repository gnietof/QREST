package com.gnf.qrest.circuit;

public class AncillaRegister extends Register {

	public AncillaRegister(int qubits) {
		super(qubits);
	}

	public AncillaRegister(int qubits,String name) {
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
		return "a";
	}
	
}
