package com.gnf.qrest.simulator;

public class SamplerRequest {

  private String circuit;
  private int shots;

  public String getCircuit() {
    return circuit;
  }

  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  public int getShots() {
    return shots;
  }

  public void setShots(int shots) {
    this.shots = shots;
  }

}
