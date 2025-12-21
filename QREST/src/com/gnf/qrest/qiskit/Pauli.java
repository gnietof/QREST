package com.gnf.qrest.qiskit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gnf.qrest.serializers.PauliSerializer;

@JsonSerialize(using = PauliSerializer.class)
public class Pauli {
	
	String label;
//	Complex coeff;
	double coeff;
	
	public Pauli(String label) {
		this.label = label;
//		this.coeff = new Complex(1.0, 0.0);
		this.coeff = 1.0;
	}

//	public Pauli(String label, Complex coeff) {
	public Pauli(String label, double coeff) {
		this.label = label;
		this.coeff = coeff;
	}

	public Pauli(String label, int[] indices, double coeff) {
		System.out.println("\n\n"+label+" : "+Arrays.toString(indices));
		int num = check(indices);
		int num2 = label.length();
		if (num>num2) {
			throw new IllegalArgumentException(String.format("Index for the qubits must be between 0 and %d.",num2-1));
		}
		char[] pp = new char[num2];
		Arrays.fill(pp, 'I');
		for (int i=0;i<indices.length;i++) {
			int pos = num2-indices[i]-1;
//			pp[pos]=label.charAt(indices[i]);
			pp[pos]=label.charAt(i);
//			pp[indices[i]]=label.charAt(i);
			System.out.println(i+"->"+pos+":"+indices[i]+"="+label.charAt(indices[i])+"->"+new String(pp));
		}
		this.label = new String(pp);
		System.out.println(this.label);
		this.coeff = coeff;
	}

	private int check(int[] indices) {
		int num = -1;
		Set<Integer> set = new HashSet<Integer>(); // Might use a boolean array but I do not know the length in advance
		for (int i=0; i<indices.length; i++) {
			if (indices[i]<0) {
				throw new IllegalArgumentException("Index for the qubits must be positive.");
			} else {
				if (set.add(indices[i])) {
					num = Math.max(num, indices[i]);
				} else {
					throw new IllegalArgumentException(String.format("Index for each qubit must be unique.",num));
				}
			}
		}
		return num;
	}

	protected static Pauli extend(Pauli pauli, int num2) {
		int num = pauli.getLabel().length();
		if (num2<num) {
			throw new IllegalArgumentException(String.format("Number of qubits must be equal or higher than %d",num));
		} 
		String label = "I".repeat(num2-num)+pauli.getLabel();
		Pauli pauli2 = new Pauli(label,pauli.getCoeff());
		return pauli2;
		
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

//	public Complex getCoeff() {
	public double getCoeff() {
		return coeff;
	}

//	public void setCoeff(Complex coeff) {
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	@Override
	public String toString() {
		return String.format("(%s, %f)",label,coeff);
	}
}
