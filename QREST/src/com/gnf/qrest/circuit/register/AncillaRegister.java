package com.gnf.qrest.circuit.register;

public class AncillaRegister extends QRegister {

	public AncillaRegister(int qubits) {
		super(qubits);
	}

	public AncillaRegister(int qubits,String name) {
		super(qubits,name);
	}

	@Override
	protected String getPrefix() {
		return "a";
	}
	
}
