package com.gnf.qrest.model;

/**
 * Models a BackendsRequest.
 */
public class BackendsRequest {
  private String name;
  private Integer minNumQubits;
  
  /**
   * BackendsRequest constructor from builder.
   * 
   * @param builder The builder to use.
   */
  public BackendsRequest(Builder builder) {
    this.name = builder.name;
    this.minNumQubits = builder.minNumQubits;
  }
  
  /**
   * Builder for Builder.
   */
  public static class Builder {
    private String name;
    private Integer minNumQubits;
    
    /**
     * Build using name. 
     * 
     * @param name The name.
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Build using number of qubits. 
     * 
     * @param minNumQubits The number of qubits.
     */
    public Builder minNumQubits(Integer minNumQubits) {
      this.minNumQubits = minNumQubits;
      return this;
    }
    
    /**
     * Return an instance of BackendsRequest. 
     */
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
