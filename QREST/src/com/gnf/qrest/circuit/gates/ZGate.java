package com.gnf.qrest.circuit.gates;

public class ZGate extends Gate1Q {

	public ZGate(int qubit) {
		super(qubit);
	}

	@Override
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("z(");
		sb.append(getQubit());
		sb.append(")");
		return sb.toString();
	}
	
	
}
