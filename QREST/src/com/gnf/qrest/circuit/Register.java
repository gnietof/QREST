package com.gnf.qrest.circuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Register {
	
	protected static Map<String,Register> registers = new HashMap<String,Register>();

	private int xbits;
	private String name;
	
	private int baseBit;

	
	public Register(int xbits) {
		this.xbits = xbits;
		String name2 = findName(getPrefix());
		this.setName(name2);
		registers.put(name2, this);
	}

	public Register(int xbits, String name) {
		this.xbits = xbits;
		this.name = name;
		registers.put(name, this);
	}

	public int getXBits() {
		return xbits;
	}

	protected void setXBits(int bits) {
		this.xbits = bits;
	}

	protected String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	protected String findName(String prefix) {
		String givenName;
		int n = 0;
		do {
			givenName = prefix+n++;
		} while (registers.containsKey(givenName));
		return givenName;
	}

	protected void checkName(String name) {
		if (registers.containsKey(name)) {
			throw new IllegalArgumentException("Register with name "+name+ "already exists.");
		}
	}

	public static List<String> getRegisters() {
		
		List<String> names = new ArrayList<String>();
		
		for (String name : registers.keySet()) {
			Register register = registers.get(name);
			String type = register instanceof QuantumRegister? "Q":"C";
			
			names.add(String.format("%s (%d-%s)",name,register.getXBits(),type));
		}
		
		return names.stream().sorted().collect(Collectors.toList());
	}
	
	protected abstract String getPrefix();

	public int getBaseBit() {
		return baseBit;
	}

	public void setBaseBit(int baseBit) {
		this.baseBit = baseBit;
	} 
	
}
