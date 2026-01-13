package com.gnf.qrest.circuit;

public class ClassicalRegister extends Register {

	public ClassicalRegister(int bits) {
		super(bits);
	}

	public ClassicalRegister(int bits,String name) {
		super(bits,name);
	}

	public int getBits() {
		return getXBits();
	}

	public void setBits(int bits) {
		this.setXBits(bits);
	}
	
	@Override
	protected String getPrefix() {
		return "c";
	}
}
