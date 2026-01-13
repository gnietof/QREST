package com.gnf.qrest.circuit.gates;

import java.util.List;
import java.util.stream.Collectors;

public class CXGate extends Gate {

	private Integer qubit;
	private List<Integer> cqubits;
	
	public CXGate(List<Integer> cqubits,Integer qubit) {
		this.qubit = qubit;
		this.cqubits = cqubits;
	}

	public CXGate(Integer cqubit,Integer qubit) {
		this.qubit = qubit;
		this.cqubits = List.of(cqubit);
	}

	public int getQubit() {
		return qubit;
	}

	public void setQubit(int qubit) {
		this.qubit = qubit;
	}

	public List<Integer> getCQqubit() {
		return cqubits;
	}

	public void setCQubit(List<Integer> cqubits) {
		this.cqubits = cqubits;
	}
	
	@Override
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("cx(");
		if (cqubits.size()>1) {
			sb.append("(");
			sb.append(cqubits.stream().map(String::valueOf).collect(Collectors.joining(",")));
			sb.append(")");
		} else {
			sb.append(qubit);
		}
		sb.append(",");
		sb.append(qubit);
		sb.append(")");
		return sb.toString();
	}

}
