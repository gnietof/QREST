package com.gnf.qrest.circuit;

import java.util.List;

public class QuantumCircuit {

	List<QuantumRegister> quregs;
	List<ClassicalRegister> clregs;
	List<Gate> gates;
	
	public QuantumCircuit() {
		
	}
	
	public QuantumCircuit(int qubits) {
		quregs.add(new QuantumRegister(qubits));
	}
	
//	public QuantumCircuit(int qubits, int bits) {
//		quregs.add(new QuantumRegister(qubits));
//		clregs.add(new ClassicalRegister(qubits));
//	}
//	
//	public QuantumCircuit(int qubits, int bits) {
//		quregs.add(new QuantumRegister(qubits));
//		clregs.add(new ClassicalRegister(qubits));
//	}
	
}
