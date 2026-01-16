package com.gnf.qrest.circuit.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Register {
	
	protected static Map<String,Register> registers = new HashMap<String,Register>();

	private int xbits;
	private int base;
	private String name;
	
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

	protected int getXBits() {
		return xbits;
	}

	protected void setXBits(int bits) {
		this.xbits = bits;
	}

	public String getName() {
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
	
	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	} 
	
	public List<Integer> get(int qubit) {
		return List.of(getBase()+qubit);
	}

	public List<Integer> get(int qubit1, int qubit2) {
		return IntStream.range(getBase()+qubit1, getBase()+qubit2).boxed().toList();
	}

	protected abstract String getPrefix();

}
