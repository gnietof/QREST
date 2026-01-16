package com.gnf.qrest.circuit;

import java.util.List;

import com.gnf.qrest.circuit.register.ClassicalRegister;
import com.gnf.qrest.circuit.register.QuantumRegister;
import com.gnf.qrest.circuit.register.Register;

public class CTest {

	public static void main(String[] args) {
		CTest ct = new CTest();
		ct.doSimple();
	} 
	
	private void doSimple() {

		QuantumRegister qr1 = new QuantumRegister(1,"q1");
		QuantumRegister qr2 = new QuantumRegister(2,"q2");
		QuantumRegister qr3 = new QuantumRegister(3,"q3");
//		ClassicalRegister cr1 = new ClassicalRegister(1);
//		ClassicalRegister cr2 = new ClassicalRegister(2);
		
		List<String> names = Register.getRegisters();
		for (String name: names) {
			System.out.println(name);
		}
		
		QuantumCircuit qc = new QuantumCircuit(qr3,qr2,qr1);
//		qc.h(0);
//		qc.x(1);
//		qc.h(1);
//		qc.cx(0,1);
//		qc.cx(1,2);
//		qc.cx(List.of(0,1),2);
//		qc.cx(qr2,qr3);
		
		qc.x(qr3.get(1));
		qc.y(qr2.get(0,2));
		qc.z(qr1);
		
		System.out.println(qc.dump());
	}
	
	
	
}
