package com.gnf.qrest.transpilation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.model.Paulis;
import java.util.List;

/**
 * Models a LayoutRequest.
 */
public class LayoutRequest {

  private String circuit;
  private String backend;
  private List<Paulis> observables;
  private boolean trimObservables = false;

  @JsonProperty("optimization_level")
  private int optimizationLevel = 1;

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
   * @param circuit The circuit to set.
   */
  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  /**
   * Gets the backend.
   *
   * @return The backend.
   */
  public String getBackend() {
    return backend;
  }

  /**
   * Sets the backend.
   *
   * @param backend The backend to set.
   */
  public void setBackend(String backend) {
    this.backend = backend;
  }

  /**
   * Gets the observables.
   *
   * @return The observables.
   */
  public List<Paulis> getObservables() {
    return observables;
  }

  /**
   * Sets the observables.
   *
   * @param observables The observables to set.
   */
  public void setObservables(List<Paulis> observables) {
    this.observables = observables;
  }

  /**
   * Gets the trimObservables.
   *
   * @return The trimObservables.
   */
  public boolean isTrimObservables() {
    return trimObservables;
  }

  /**
   * Sets the trimObservables.
   *
   * @param trimObservables The trimObservables to set.
   */
  public void setTrimObservables(boolean trimObservables) {
    this.trimObservables = trimObservables;
  }

  /**
   * Gets the optimizationLevel.
   *
   * @return The optimizationLevel.
   */
  public int getOptimizationLevel() {
    return optimizationLevel;
  }

  /**
   * Sets the optimizationLevel.
   *
   * @param optimizationLevel The optimizationLevel to set.
   */
  public void setOptimizationLevel(int optimizationLevel) {
    this.optimizationLevel = optimizationLevel;
  }

}
