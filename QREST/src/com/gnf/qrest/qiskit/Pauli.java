package com.gnf.qrest.qiskit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Models a Pauli.
 */
/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
public class Pauli {

  String label;
  double coeff;

  /**
   * Default Pauli constructor.
   */
  public Pauli() {
  }

  /**
   * Pauli constructor from label.
   */
  public Pauli(String label) {
    this.label = label;
    this.coeff = 1.0;
  }

  /**
   * Pauli constructor from label and coefficient.
   */
  public Pauli(String label, double coeff) {
    this.label = label;
    this.coeff = coeff;
  }

  /**
   * Pauli constructor from label,indices and coefficient.
   */
  public Pauli(String label, int[] indices, double coeff) {
    int num = check(indices);
    int num2 = label.length();
    if (num > num2) {
      throw new IllegalArgumentException(
          String.format("Index for the qubits must be between 0 and %d.", num2 - 1));
    }
    char[] pp = new char[num2];
    Arrays.fill(pp, 'I');
    for (int i = 0; i < indices.length; i++) {
      int pos = num2 - indices[i] - 1;
      pp[pos] = label.charAt(i);
    }
    this.label = new String(pp);
    this.coeff = coeff;
  }

  private int check(int[] indices) {
    int num = -1;
    // Might use a boolean array but I do not know the length in advance
    Set<Integer> set = new HashSet<Integer>();
    for (int i = 0; i < indices.length; i++) {
      if (indices[i] < 0) {
        throw new IllegalArgumentException("Index for the qubits must be positive.");
      } else {
        if (set.add(indices[i])) {
          num = Math.max(num, indices[i]);
        } else {
          throw new IllegalArgumentException(
              String.format("Index for each qubit must be unique.", num));
        }
      }
    }
    return num;
  }

  protected static Pauli extend(Pauli pauli, int num) {
    int num2 = pauli.getLabel().length();
    if (num < num2) {
      throw new IllegalArgumentException(
          String.format("Number of qubits must be equal or higher than %d", num2));
    }
    String label = "I".repeat(num - num2) + pauli.getLabel();
    Pauli pauli2 = new Pauli(label, pauli.getCoeff());
    return pauli2;

  }

  /**
   * Gets the label.
   * 
   * @return The label.
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label.
   * 
   * @param label the label to set
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Gets the coeff.
   * 
   * @return The coeff.
   */
  public double getCoeff() {
    return coeff;
  }

  /**
   * Sets the coeff.
   * 
   * @param coeff the coeff to set
   */
  public void setCoeff(double coeff) {
    this.coeff = coeff;
  }

  @Override
  public String toString() {
    return String.format("(%s, %f)", label, coeff);
  }

}
