package com.gnf.qrest.simulator;

/**
 * Models a request to run an Estimator.
 */
public class EstimatorRequest {

  private String circuit;
  private String observable;
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
   * Gets the observable.
   *
   * @return The observable.
   */
  public String getObservable() {
    return observable;
  }

  /**
   * Sets the observable.
   *
   * @param observable the observable to set.
   */
  public void setObservable(String observable) {
    this.observable = observable;
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
