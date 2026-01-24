package com.gnf.qrest.transpilation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TranspileRequest {

  private String circuit;
  private String backend;

  @JsonProperty("optimization_level")
  private int optimizationLevel = 1;

  public String getCircuit() {
    return circuit;
  }

  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  public String getBackend() {
    return backend;
  }

  public void setBackend(String backend) {
    this.backend = backend;
  }

  public int getOptimizationLevel() {
    return optimizationLevel;
  }

  public void setOptimizationLevel(int optimizationLevel) {
    this.optimizationLevel = optimizationLevel;
  }

}
