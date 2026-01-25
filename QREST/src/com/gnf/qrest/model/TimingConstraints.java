package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a TimingConstraints.
 */
public class TimingConstraints {

  @JsonProperty("acquire_alignment")
  private int acquireAlignment;

  private int granularity;

  @JsonProperty("min_length")
  private int minLength;

  @JsonProperty("pulse_alignment")
  private int pulseAlignment;

  /**
   * Gets the acquireAlignment.
   *
   *  @return the acquireAlignment
   */
  public int getAcquireAlignment() {
    return acquireAlignment;
  }

  /**
   * Sets the acquireAlignment.
   *
   * @param acquireAlignment the acquireAlignment to set
   */
  public void setAcquireAlignment(int acquireAlignment) {
    this.acquireAlignment = acquireAlignment;
  }

  /**
   * Gets the granularity.
   *
   *  @return the granularity
   */
  public int getGranularity() {
    return granularity;
  }

  /**
   * Sets the granularity.
   *
   * @param granularity the granularity to set
   */
  public void setGranularity(int granularity) {
    this.granularity = granularity;
  }

  /**
   * Gets the minLength.
   *
   *  @return the minLength
   */
  public int getMinLength() {
    return minLength;
  }

  /**
   * Sets the minLength.
   *
   * @param minLength the minLength to set
   */
  public void setMinLength(int minLength) {
    this.minLength = minLength;
  }

  /**
   * Gets the pulseAlignment.
   *
   *  @return the pulseAlignment
   */
  public int getPulseAlignment() {
    return pulseAlignment;
  }

  /**
   * Sets the pulseAlignment.
   *
   * @param pulseAlignment the pulseAlignment to set
   */
  public void setPulseAlignment(int pulseAlignment) {
    this.pulseAlignment = pulseAlignment;
  }


}