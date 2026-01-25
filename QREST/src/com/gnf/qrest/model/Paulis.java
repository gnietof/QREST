package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.gnf.qrest.qiskit.Pauli;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Models a Paulis (List of Pauli).
 */
public final class Paulis implements Iterable<Pauli> {

  private final List<Pauli> paulis;


  /**
   * Default constructor.
   */
  public Paulis() {
    paulis = new ArrayList<Pauli>();
  }

  /**
   * Constructor from list of Pauli.
   */
  @JsonCreator
  public Paulis(List<Pauli> paulis) {
    this.paulis = List.copyOf(paulis); // defensive copy
  }

  /**
   * Constructor from list of Pauli.
   * 
   * @param paulis The Paulis.
   */
  public Paulis(Pauli... paulis) {
    this.paulis = List.of(paulis);
  }

  /**
   * Returns the Paulis as a List<Pauli>.
   */
  @JsonValue
  public List<Pauli> asList() {
    return paulis;
  }

  
  /**
   * Returns the size of the list.
   * 
   * @return The size of the list.
   */
  public int size() {
    return paulis.size();
  }

  /**
   * Adds a Pauli to the list.
   * 
   * @param pauli The Pauli to add.
   */
  public void add(Pauli pauli) {
    paulis.add(pauli);
  }

  /**
   * Adds a collection of Pauli to the list.
   * 
   * @param paulis The collection of Pauli to add.
   */
  public void addAll(Collection<? extends Pauli> paulis) {
    this.paulis.addAll(paulis);
  }

  /**
   * Returns a Pauli from the list.
   * 
   * @param index The index of the Pauli.
   */
  public Pauli get(int index) {
    return paulis.get(index);
  }

  @Override
  public Iterator<Pauli> iterator() {
    return paulis.iterator();
  }

  /**
   * Returns the list of Pauli as a stream.
   */
  public Stream<Pauli> stream() {
    return paulis.stream();
  }
}
