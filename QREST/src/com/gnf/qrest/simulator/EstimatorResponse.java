package com.gnf.qrest.simulator;

import java.util.List;

/**
 * Models an Estimator response 
 */
public class EstimatorResponse {

  private List<Double> result;

  /**
   * @return the result
   */
  public List<Double> getResult() {
    return result;
  }

  /**
   * @param result the result to set
   */
  public void setResult(List<Double> result) {
    this.result = result;
  }


}
