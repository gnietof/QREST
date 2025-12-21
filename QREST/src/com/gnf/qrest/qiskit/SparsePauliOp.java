package com.gnf.qrest.qiskit;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SparsePauliOp {
	
	private String type = "SparsePauliOp";
	private List<Pauli> paulis;
	
	public static void main(String[] args) {
		SparsePauliOp sp = SparsePauliOp.fromSparseList(List.of(
				new Pauli("ZYIIX",new int[]{1,4,2,3,0},1.0)),5);
		System.out.println("ZYIIX:"+sp);
		sp = SparsePauliOp.fromSparseList(List.of(
				new Pauli("ZIIIX",new int[]{1,4},1.0)),5);
		System.out.println(sp);
		sp = SparsePauliOp.fromSparseList(List.of(
			new Pauli("ZIIIIXY",new int[]{0,6},1.0),
			new Pauli("ZIIIX",new int[]{1,4},1.0),
			new Pauli("ZXI",new int[]{2,1},1.0),
			new Pauli("YIIY",new int[]{0,3},2.0)),7);
		System.out.println(sp);
		sp = SparsePauliOp.fromSparseList(List.of(
			new Pauli("XZZZZZZ",new int[]{1,2,0,3},1.0)),7);
		System.out.println(sp);
	}
	
	private SparsePauliOp(List<Pauli> paulis) {
		this.paulis = paulis;
	}

	public static SparsePauliOp fromList(List<Pauli> paulis) {
		SparsePauliOp sop = new SparsePauliOp(paulis);
		return sop;
	}

	public static SparsePauliOp fromSparseList(List<Pauli> paulis,int num) {
		List<Pauli> pp = new ArrayList<Pauli>();
		for (Pauli p : paulis) {
			pp.add(Pauli.extend(p, num));
		}
		SparsePauliOp sop = new SparsePauliOp(pp);
		return sop;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Pauli> getPaulis() {
		return paulis;
	}

	public void setPaulis(List<Pauli> paulis) {
		this.paulis = paulis;
	}
	
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",","[","]");
		for (Pauli p: paulis) {
			sj.add(p.toString());
		}
		return sj.toString();
	}
	
}
