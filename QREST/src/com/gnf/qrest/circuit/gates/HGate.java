package com.gnf.qrest.circuit.gates;

public class HGate extends Gate1Q {

	private int qubit;
	
	public HGate(int qubit) {
		super(qubit);
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
