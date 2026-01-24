package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Information about the QPU backend. 
 */
public class Backend {
  private String name;
  private String state;
  private String message;

  @JsonProperty("is_simulator")
  private boolean isSimulator;

  private int qubits;
  
  @JsonProperty("length_queue")
  private int queueLength;
  
  @JsonProperty("backend_version")
  private String backendVersion;

  /**
   * Gets the name.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name The name to set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the state.
   *
   * @return The state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state The state to set.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the message.
   *
   * @return The message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message The message to set.
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the isSimulator.
   *
   * @return The isSimulator.
   */
  public boolean isSimulator() {
    return isSimulator;
  }

  /**
   * Sets the isSimulator.
   *
   * @param isSimulator The isSimulator to set.
   */
  public void setSimulator(boolean isSimulator) {
    this.isSimulator = isSimulator;
  }

  /**
   * Gets the qubits.
   *
   * @return The qubits.
   */
  public int getQubits() {
    return qubits;
  }

  /**
   * Sets the qubits.
   *
   * @param qubits The qubits to set.
   */
  public void setQubits(int qubits) {
    this.qubits = qubits;
  }

  /**
   * Gets the queueLength.
   *
   * @return The queueLength.
   */
  public int getQueueLength() {
    return queueLength;
  }

  /**
   * Sets the queueLength.
   *
   * @param queueLength The queueLength to set.
   */
  public void setQueueLength(int queueLength) {
    this.queueLength = queueLength;
  }

  /**
   * Gets the backendVersion.
   *
   * @return The backendVersion.
   */
  public String getBackendVersion() {
    return backendVersion;
  }

  /**
   * Sets the backendVersion.
   *
   * @param backendVersion The backendVersion to set.
   */
  public void setBackendVersion(String backendVersion) {
    this.backendVersion = backendVersion;
  }
  
}
