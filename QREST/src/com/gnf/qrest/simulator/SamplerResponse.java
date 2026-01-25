package com.gnf.qrest.simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Models a Sampler response.
 */
public class SamplerResponse {

  private Result result;

  /**
   * Models a Sampler results.
   */
  public static class Result {

    @JsonProperty("quasi_dists")
    private List<Map<Integer, Double>> quasiDists;

    /**
     * Gets the quasiDists.
     *
     * @return The quasiDists.
     */
    public List<Map<Integer, Double>> getQuasiDists() {
      return quasiDists;
    }

    /**
     * Sets the quasiDists.
     *
     * @param quasiDists the quasiDists to set.
     */
    public void setQuasiDists(List<Map<Integer, Double>> quasiDists) {
      this.quasiDists = quasiDists;
    }

  }

  /**
   * Gets the result.
   *
   * @return The result.
   */
  public Result getResult() {
    return result;
  }

  /**
   * Sets the result.
   *
   * @param result the result to set.
   */
  public void setResult(Result result) {
    this.result = result;
  }

}
