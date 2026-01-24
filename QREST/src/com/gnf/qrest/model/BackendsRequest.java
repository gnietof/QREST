package com.gnf.qrest.model;

public class BackendsRequest {
  private String name;
  private Integer minNumQubits;

  public BackendsRequest(Builder builder) {
    this.name = builder.name;
    this.minNumQubits = builder.minNumQubits;
  }

  public static class Builder {
    private String name;
    private Integer minNumQubits;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder minNumQubits(Integer minNumQubits) {
      this.minNumQubits = minNumQubits;
      return this;
    }

    public BackendsRequest build() {
      return new BackendsRequest(this);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMinNumQubits() {
    return minNumQubits;
  }

  public void setMinNumQubits(Integer minNumQubits) {
    this.minNumQubits = minNumQubits;
  }

}
