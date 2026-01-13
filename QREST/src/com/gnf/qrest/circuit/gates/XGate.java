package com.gnf.qrest.circuit.gates;

public class XGate extends Gate {

	private int qubit;
	
	public XGate(int qubit) {
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
		sb.append("x(");
		sb.append(qubit);
		sb.append(")");
		return sb.toString();
	}
	
	
}
