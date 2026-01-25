package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Models a BitString.
 */
public final class BitString implements Iterable<String> {

  private final List<String> strings;

  /**
   * Default constructor.
   */
  public BitString() {
    strings = new ArrayList<String>();
  }

  /**
   * Constructor from list of strings.
   *
   * @param strings The list of strings.
   */
  @JsonCreator
  public BitString(List<String> strings) {
    this.strings = List.copyOf(strings); // defensive copy
  }

  /**
   * Returns the bitstring as a list of strings.
   *
   * @return The list of strings.
   */
  @JsonValue
  public List<String> asList() {
    return strings;
  }

  /**
   * Returns the size of the bitstring.
   *
   * @return The size of the bitstring.
   */
  public int size() {
    return strings.size();
  }

  /**
   * Adds a string to the bitstring.
   *
   * @param string The string to addd.
   */
  public void add(String string) {
    strings.add(string);
  }

  /**
   * Adds a collection of strings to the bitstring.
   *
   * @param strings The collection of strings to addd.
   */
  public void addAll(Collection<String> strings) {
    this.strings.addAll(strings);
  }

  /**
   * Retrieves a string from the bitstring.
   *
   * @param index The index of the string.
   */
  public String get(int index) {
    return strings.get(index);
  }

  @Override
  public Iterator<String> iterator() {
    return strings.iterator();
  }

  /**
   * Returns the bitstring as a stream of strings.
   *
   * @return The list of strings.
   */
  public Stream<String> stream() {
    return strings.stream();
  }

  @Override
  public String toString() {
    return String.join(", ", strings);
  }
}
