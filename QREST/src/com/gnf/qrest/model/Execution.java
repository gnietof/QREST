package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a Execution.
 */
public class Execution {
  @JsonProperty("init_qubits")
  private boolean initQubits = true;

  /**
   * Gets the initQubits.
   *
   * @return The initQubits.
   */
  public boolean isInitQubits() {
    return initQubits;
  }

  /**
   * Sets the initQubits.
   *
   * @param initQubits The initQubits to set.
   */
  public void setInitQubits(boolean initQubits) {
    this.initQubits = initQubits;
  }

}
