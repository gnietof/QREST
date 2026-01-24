package com.gnf.qrest.transpilation;

import com.gnf.qrest.model.Paulis;
import java.util.List;

public class LayoutResponse {

  private String qasm;

  private List<Paulis> observables;

  public String getQASM() {
    return qasm;
  }

  public void setQASM(String qasm) {
    this.qasm = qasm;
  }

  public List<Paulis> getObservables() {
    return observables;
  }

  public void setObservables(List<Paulis> observables) {
    this.observables = observables;
  }

}
