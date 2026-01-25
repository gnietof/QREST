package com.gnf.qrest.transpilation;

import com.gnf.qrest.model.Paulis;
import java.util.List;

/**
 * Models a LayoutResponse.
 */
public class LayoutResponse {

  private String qasm;

  private List<Paulis> observables;

  /**
   * Gets the qasm.
   *
   *  @return The qasm.
   */
  public String getQASM() {
    return qasm;
  }

  /**
   * Sets the qasm.
   * 
   * @param qasm The qasm to set.
   */
  public void setQASM(String qasm) {
    this.qasm = qasm;
  }

  /**
   * Gets the observables.
   *
   *  @return The observables.
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


}
