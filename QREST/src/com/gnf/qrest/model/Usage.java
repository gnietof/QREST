package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a Usage.
 */
public class Usage {

  @JsonProperty("quantum_seconds")
  private int quantumSeconds;

  private int seconds;

  /**
   * Gets the quantumSeconds.
   * 
   * @return The quantumSeconds.
   */
  public int getQuantumSeconds() {
    return quantumSeconds;
  }

  /**
   * Sets the quantumSeconds.
   * 
   * @param quantumSeconds the quantumSeconds to set
   */
  public void setQuantumSeconds(int quantumSeconds) {
    this.quantumSeconds = quantumSeconds;
  }

  /**
   * Gets the seconds.
   * 
   * @return The seconds.
   */
  public int getSeconds() {
    return seconds;
  }

  /**
   * Sets the seconds.
   * 
   * @param seconds the seconds to set
   */
  public void setSeconds(int seconds) {
    this.seconds = seconds;
  }


}
