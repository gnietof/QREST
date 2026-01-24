package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimingConstraints {

  @JsonProperty("acquire_alignment")
  private int acquireAlignment;

  private int granularity;

  @JsonProperty("min_length")
  private int minLength;

  @JsonProperty("pulse_alignment")
  private int pulseAlignment;

  public int getAcquireAlignment() {
    return acquireAlignment;
  }

  public void setAcquireAlignment(int acquireAlignment) {
    this.acquireAlignment = acquireAlignment;
  }

  public int getGranularity() {
    return granularity;
  }

  public void setGranularity(int granularity) {
    this.granularity = granularity;
  }

  public int getMinLength() {
    return minLength;
  }

  public void setMinLength(int minLength) {
    this.minLength = minLength;
  }

  public int getPulseAlignment() {
    return pulseAlignment;
  }

  public void setPulseAlignment(int pulseAlignment) {
    this.pulseAlignment = pulseAlignment;
  }

}