package com.gnf.qrest.model;

import java.util.Collections;
import java.util.List;

/**
 * PUB abstract class.
 */
public abstract class PUB {

  private String circuit;
  private List<List<Double>> parameters;

  /**
   * PUB constructor from builder.
   * 
   * @param builder The builder to use.
   */
  public PUB(Builder<?> builder) {
    this.circuit = builder.circuit;
    this.parameters = builder.parameters;
  }

  abstract static class Builder<T extends Builder<T>> {
    private String circuit;
    private List<List<Double>> parameters = Collections.emptyList();

    public T circuit(String circuit) {
      this.circuit = circuit;
      return self();
    }

    public T parameters(List<List<Double>> parameters) {
      this.parameters = parameters;
      return self();
    }

    protected abstract T self();

    abstract PUB build();

  }

  /**
   * Gets the circuit.
   * 
   * @return The circuit.
   */
  public String getCircuit() {
    return circuit;
  }

  /**
   * Sets the circuit.
   * 
   * @param circuit the circuit to set
   */
  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  /**
   * Gets the parameters.
   * 
   * @return The parameters.
   */
  public List<List<Double>> getParameters() {
    return parameters;
  }

  /**
   * Sets the parameters.
   * 
   * @param parameters the parameters to set
   */
  public void setParameters(List<List<Double>> parameters) {
    this.parameters = parameters;
  }

}
