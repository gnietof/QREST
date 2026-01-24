package com.gnf.qrest.transpilation;

import com.gnf.qrest.model.Paulis;
import java.util.List;

public class LayoutResponse {

  private String qasm;

  private List<Paulis> observables;

  /**
   * Returns the QASM property of this instance.
   * 
   * @return The QASM property of this instance.
   */
  public String getQASM() {
    return qasm;
  }

  /**
   * Sets the qasm property of this instance.
   * 
   * @param qasm The qasm value to be set.
   */
  public void setQASM(String qasm) {
    this.qasm = qasm;
  }

  /**
   * Returns the observables property of this instance.
   * 
   * @return The observables property of this instance.
   */
  public List<Paulis> getObservables() {
    return observables;
  }

  /**
   * Sets the observables property of this instance.
   * 
   * @param observables The observables value to be set.
   */
  public void setObservables(List<Paulis> observables) {
    this.observables = observables;
  }

}
