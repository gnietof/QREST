package com.gnf.qrest.model.complex;

//@JsonSerialize(using = ComplexSerializer.class)
public class Complex {

	private Double real;
	private Double imag;
	
	public Complex(Double real, Double imag) {
		this.real = real;
		this.imag = imag;
	}

	public Double getReal() {
		return real;
	}

	public void setReal(Double real) {
		this.real = real;
	}

	public Double getImag() {
		return imag;
	}

	public void setImag(Double imag) {
		this.imag = imag;
	}
	
}
