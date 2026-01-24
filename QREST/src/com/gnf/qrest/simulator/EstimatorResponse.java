package com.gnf.qrest.simulator;

import java.util.List;

/**
 * Models an Estimator response.
 */
public class EstimatorResponse {

  private List<Double> result;

  /**
   * Gets the result.
   * 
   * @return The result.
   */
  public List<Double> getResult() {
    return result;
  }

  /**
   * Sets the result.
   * 
   * @param result The result to set.
   */
  public void setResult(List<Double> result) {
    this.result = result;
  }



}
