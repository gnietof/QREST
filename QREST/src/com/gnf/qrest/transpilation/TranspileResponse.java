package com.gnf.qrest.transpilation;

/**
 * Models a TranspileResponse.
 */
public final class TranspileResponse {

  private String qasm;

  /**
   * Gets the qasm.
   *
   * @return The qasm.
   */
  public String getQASM() {
    return qasm;
  }

  /**
   * Sets the qasm.
   *
   * @param qasm the qasm to set
   */
  public void setQASM(String qasm) {
    this.qasm = qasm;
  }

}
