package com.gnf.qrest.circuit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gnf.qrest.circuit.gates.CXGate;
import com.gnf.qrest.circuit.gates.Gate;
import com.gnf.qrest.circuit.gates.HGate;
import com.gnf.qrest.circuit.gates.XGate;

public class QuantumCircuit {

	List<QuantumRegister> quregs = new ArrayList<QuantumRegister>();
	List<ClassicalRegister> clregs = new ArrayList<ClassicalRegister>();
	List<Gate> gates = new ArrayList<Gate>();
	
	public QuantumCircuit() {
		
	}
	
	public QuantumCircuit(int qubits) {
		quregs.add(new QuantumRegister(qubits));
	}
	
	public QuantumCircuit(int qubits, Integer bits) {
		quregs.add(new QuantumRegister(qubits));
		clregs.add(new ClassicalRegister(qubits));
	}
	
	public QuantumCircuit(Register... registers) {
		for (Register register: registers) {
			if (register instanceof QuantumRegister) {
				quregs.add((QuantumRegister) register);
			}
			if (register instanceof ClassicalRegister) {
				clregs.add((ClassicalRegister) register);
			}
		}
	}
	
//	public QuantumCircuit(int qubits, int bits) {
//		quregs.add(new QuantumRegister(qubits));
//		clregs.add(new ClassicalRegister(qubits));
//	}
	
	public void addRegister(Register r) {
		
		if (r instanceof QuantumRegister) {
			quregs.add((QuantumRegister) r);
		}
		
		if (r instanceof ClassicalRegister) {
			quregs.add((QuantumRegister) r);
		}
		
		
	}
	
	public void x(int ...qubits) {
		for (int qubit: qubits) {
			gates.add(new XGate(qubit));
		}
	}
	
	public void x(QuantumRegister register) {
		for (int i=0; i<register.getQubits(); i++) {
			gates.add(new XGate(getLocalQubit(register, i)));
		}
	}
	
	public void h(int ...qubits) {
		for (int qubit: qubits) {
			gates.add(new HGate(qubit));
		}
	}
	
	public void h(List<Integer> qubits) {
		for (int qubit: qubits) {
			gates.add(new HGate(qubit));
		}
	}
	
	public void h(QuantumRegister register) {
		for (int i=0; i<register.getQubits(); i++) {
			gates.add(new HGate(getLocalQubit(register, i)));
		}
	}
	
	public void cx(int cqubit, int qubit) {
		gates.add(new CXGate(cqubit,qubit));
	}
	
	public void cx(List<Integer> cqubits, int qubit) {
		for (int cqubit: cqubits) {
			gates.add(new CXGate(cqubit,qubit));
		}
	}
	
	public void cx(int cqubit, List<Integer> qubits) {
		for (int qubit: qubits) {
			gates.add(new CXGate(cqubit,qubit));
		}
	}
	
	public void cx(List<Integer> cqubits, List<Integer> qubits) {
		for (int i=0; i<cqubits.size(); i++) {
			gates.add(new CXGate(cqubits.get(i),qubits.get(i)));
		}
	}
	
	public void cx(QuantumRegister cqubits, QuantumRegister qubits) {
		for (int i=0; i<cqubits.getQubits(); i++) {
			gates.add(new CXGate(getLocalQubit(cqubits, i),getLocalQubit(qubits, i)));
		}
	}
	
	public String dump() {
		StringBuffer sb = new StringBuffer();
		
		for (Register qureg: quregs) {
			sb.append(qureg.getName());
			sb.append(" = ");
			sb.append(qureg instanceof QuantumRegister? "QuantumRegister(":"AncillaRegister(");
			sb.append(qureg.getXBits());
			sb.append(")\n");
		}
		
		for (ClassicalRegister clreg: clregs) {
			sb.append(clreg.getName());
			sb.append(" = ClassicalRegister(");
			sb.append(clreg.getXBits());
			sb.append(")\n");
		}
		
		sb.append("qc = QuantumCircuit(");
		sb.append(quregs.stream().map(r -> r.getName()).collect(Collectors.joining(",")));
		if (clregs.size()>0) {
			sb.append(",");
			sb.append(clregs.stream().map(r -> r.getName()).collect(Collectors.joining(",")));
		}
		sb.append(")\n");

		for (Gate g: gates) {
			sb.append("qc.");
			sb.append(g.dump());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	private int getLocalQubit(Register register, int qubit) {
	
		return register.getBaseBit()+qubit;
	}
	
	
}
