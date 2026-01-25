package com.gnf.qrest.qiskit;

import com.gnf.qrest.model.Paulis;
import java.util.List;
import java.util.StringJoiner;

/**
 * Models a SparsePauliOp.
 */
public class SparsePauliOp {

  private String type = "SparsePauliOp";
  private Paulis paulis;

  private SparsePauliOp(Paulis paulis) {
    this.paulis = paulis;
  }

  /**
   * Contruct a SparsePauliOp from a Paulis.
   *
   * @param paulis The paulis.
   */
  public static SparsePauliOp fromList(Paulis paulis) {
    SparsePauliOp sop = new SparsePauliOp(paulis);
    return sop;
  }

  /**
   * Contruct a SparsePauliOp from a Paulis with num qubits.
   *
   * @param paulis The paulis.
   * @param num The num of qubits.
   */
  public static SparsePauliOp fromSparseList(Paulis paulis, int num) {
    return SparsePauliOp.fromSparseList(paulis.asList(), num);
  }

  /**
   * Contruct a SparsePauliOp from a list of Paulis with num qubits.
   *
   * @param paulis The list of paulis.
   * @param num The num of qubits.
   */
  public static SparsePauliOp fromSparseList(List<Pauli> paulis, int num) {
    Paulis pp = new Paulis();
    for (Pauli p : paulis) {
      pp.add(Pauli.extend(p, num));
    }
    SparsePauliOp sop = new SparsePauliOp(pp);
    return sop;
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(",", "[", "]");
    for (Pauli p : paulis.asList()) {
      sj.add(p.toString());
    }
    return sj.toString();
  }

  /**
   * Gets the type.
   *
   * @return The type.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the paulis.
   *
   * @return The paulis.
   */
  public Paulis getPaulis() {
    return paulis;
  }

  /**
   * Sets the paulis.
   *
   * @param paulis the paulis to set
   */
  public void setPaulis(Paulis paulis) {
    this.paulis = paulis;
  }

}
