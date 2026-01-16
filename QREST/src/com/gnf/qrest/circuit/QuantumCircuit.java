package com.gnf.qrest.circuit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.gnf.qrest.circuit.gates.CXGate;
import com.gnf.qrest.circuit.gates.Gate;
import com.gnf.qrest.circuit.gates.HGate;
import com.gnf.qrest.circuit.gates.XGate;
import com.gnf.qrest.circuit.gates.YGate;
import com.gnf.qrest.circuit.gates.ZGate;
import com.gnf.qrest.circuit.register.ClassicalRegister;
import com.gnf.qrest.circuit.register.QRegister;
import com.gnf.qrest.circuit.register.QuantumRegister;
import com.gnf.qrest.circuit.register.Register;

public class QuantumCircuit {

	List<QRegister> quregs = new ArrayList<QRegister>();
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
			if (register instanceof QRegister) {
				addQRegister((QRegister) register);
			}
			if (register instanceof ClassicalRegister) {
				addCRegister((ClassicalRegister) register);
			}
		}
	}
	
	private void addQRegister(QRegister register) {
		int base=0;
		if (quregs.size()>0) {
			QRegister last = quregs.get(quregs.size()-1);
			base = last.getBase()+last.getQubits();
		}
		register.setBase(base);
		quregs.add(register);
	}

	private void addCRegister(ClassicalRegister register) {
		int base=0;
		if (quregs.size()>0) {
			ClassicalRegister last = clregs.get(clregs.size()-1);
			base = last.getBase()+last.getBits();
		}
		register.setBase(base);
		clregs.add(register);
	}

//	public QuantumCircuit(int qubits, int bits) {
//		quregs.add(new QuantumRegister(qubits));
//		clregs.add(new ClassicalRegister(qubits));
//	}
	
	public void addRegister(Register r) {
		
		if (r instanceof QRegister) {
			quregs.add((QRegister) r);
		}
		
		if (r instanceof ClassicalRegister) {
			clregs.add((ClassicalRegister) r);
		}
		
	}
	
	private <T extends Gate> void add1QGate(Function<Integer,T> factory, int... qubits) {
		for (int qubit: qubits) {
			T gate = factory.apply(qubit);
			gates.add(gate);
		}
	}

	private <T extends Gate> void add1QGate(Function<Integer,T> factory, QRegister register) {
		for (int i=0; i<register.getQubits(); i++) {
			int q = getLocalQubit(register, i);
			T gate = factory.apply(q);
			gates.add(gate);
		}
	}
	
	private <T extends Gate> void add1QGate(Function<Integer,T> factory, List<Integer> qubits) {
		for (int qubit: qubits) {
			T gate = factory.apply(qubit);
			gates.add(gate);
		}
	}
	
	public void x(int ...qubits) {
		add1QGate(XGate::new,qubits);
	}

	public void x(QuantumRegister register) {
		add1QGate(XGate::new,register);
	}

	public void x(List<Integer> qubits) {
		add1QGate(XGate::new,qubits);
	}
	
	public void y(int ...qubits) {
		add1QGate(YGate::new,qubits);
	}

	public void y(QuantumRegister register) {
		add1QGate(YGate::new,register);
	}

	public void y(List<Integer> qubits) {
		add1QGate(YGate::new,qubits);
	}
	
	public void z(int ...qubits) {
		add1QGate(ZGate::new,qubits);
	}

	public void z(QuantumRegister register) {
		add1QGate(ZGate::new,register);
	}

	public void z(List<Integer> qubits) {
		add1QGate(ZGate::new,qubits);
	}
	
	public void h(int ...qubits) {
		add1QGate(HGate::new, qubits);
	}
	
	public void h(QuantumRegister register) {
		add1QGate(HGate::new,register);
	}
	
	public void h(List<Integer> qubits) {
		add1QGate(HGate::new,qubits);
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
		
		for (QRegister qureg: quregs) {
			sb.append(qureg.getName());
			sb.append(" = ");
			sb.append(qureg instanceof QuantumRegister? "QuantumRegister(":"AncillaRegister(");
			sb.append(qureg.getQubits());
			sb.append(",'");
			sb.append(qureg.getName());
			sb.append("')\n");
		}
		
		for (ClassicalRegister clreg: clregs) {
			sb.append(clreg.getName());
			sb.append(" = ClassicalRegister(");
			sb.append(clreg.getBits());
			sb.append(",'");
			sb.append(clreg.getName());
			sb.append("')\n");
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
	
		return register.getBase()+qubit;
	}
	
	
}
