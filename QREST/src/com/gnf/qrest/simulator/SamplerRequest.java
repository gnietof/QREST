package com.gnf.qrest.simulator;

/**
 * Models a request to run an Estimator.
 */
public class SamplerRequest {

  private String circuit;
  private int shots;

  /**
   * Gets the circuit.
   * 
   * @return The circuit.
   */
  public String getCircuit() {
    return circuit;
  }

  /**
   * Sets the circuit.
   * 
   * @param circuit the circuit to set.
   */
  public void setCircuit(String circuit) {
    this.circuit = circuit;
  }

  /**
   * Gets the shots.
   * 
   * @return The shots.
   */
  public int getShots() {
    return shots;
  }

  /**
   * Sets the shots.
   * 
   * @param shots the shots to set.
   */
  public void setShots(int shots) {
    this.shots = shots;
  }


}
