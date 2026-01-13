package com.gnf.qrest.circuit.gates;

public class HGate extends Gate {

	private int qubit;
	
	public HGate(int qubit) {
		this.qubit = qubit;
	}

	public int getQubit() {
		return qubit;
	}

	public void setQubit(int qubit) {
		this.qubit = qubit;
	}

	@Override
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("h(");
		sb.append(qubit);
		sb.append(")");
		return sb.toString();
	}

}
