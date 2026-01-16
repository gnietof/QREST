package com.gnf.qrest.circuit.gates;

public class YGate extends Gate1Q {

	public YGate(int qubit) {
		super(qubit);
	}

	@Override
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("y(");
		sb.append(getQubit());
		sb.append(")");
		return sb.toString();
	}
	
	
}
