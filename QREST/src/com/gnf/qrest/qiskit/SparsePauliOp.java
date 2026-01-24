package com.gnf.qrest.qiskit;

import com.gnf.qrest.model.Paulis;
import java.util.List;
import java.util.StringJoiner;

public class SparsePauliOp {

  private String type = "SparsePauliOp";
  private Paulis paulis;

  private SparsePauliOp(Paulis paulis) {
    this.paulis = paulis;
  }

  public static SparsePauliOp fromList(Paulis paulis) {
    SparsePauliOp sop = new SparsePauliOp(paulis);
    return sop;
  }

  public static SparsePauliOp fromSparseList(Paulis paulis, int num) {
    return SparsePauliOp.fromSparseList(paulis.asList(), num);
  }

  public static SparsePauliOp fromSparseList(List<Pauli> paulis, int num) {
    Paulis pp = new Paulis();
    for (Pauli p : paulis) {
      pp.add(Pauli.extend(p, num));
    }
    SparsePauliOp sop = new SparsePauliOp(pp);
    return sop;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Paulis getPaulis() {
    return paulis;
  }

  public void setPaulis(List<Pauli> paulis) {
    this.paulis = new Paulis(paulis);
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(",", "[", "]");
    for (Pauli p : paulis.asList()) {
      sj.add(p.toString());
    }
    return sj.toString();
  }

}
