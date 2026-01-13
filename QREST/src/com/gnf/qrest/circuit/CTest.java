package com.gnf.qrest.circuit;

import java.util.List;

public class CTest {

	public static void main(String[] args) {
		CTest ct = new CTest();
		ct.doSimple();
	} 
	
	private void doSimple() {

		QuantumRegister qr1 = new QuantumRegister(1);
		QuantumRegister qr2 = new QuantumRegister(2);
		QuantumRegister qr3 = new QuantumRegister(2,"x1");
		ClassicalRegister cr1 = new ClassicalRegister(1);
		ClassicalRegister cr2 = new ClassicalRegister(2);
		
		List<String> names = Register.getRegisters();
		for (String name: names) {
			System.out.println(name);
		}
		
		QuantumCircuit qc = new QuantumCircuit(qr1,qr2,cr1);
		qc.h(0);
		qc.x(1);
		qc.h(1);
		qc.cx(0,1);
		qc.cx(1,2);
		qc.cx(List.of(0,1),2);
		qc.cx(qr2,qr3);
		
		System.out.println(qc.dump());
	}
	
	
	
}
