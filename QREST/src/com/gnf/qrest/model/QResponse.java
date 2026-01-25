package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Models a QResponse.
 */
public class QResponse {

  private List<QError> errors;
  private String trace;

  /**
   * Models a QError. 
   */
  public static class QError {

    private int code;
    private String message;
    private String solution;

    @JsonProperty("more_info")
    private String moreInfo;

    /**
     * Gets the code.
     *
     * @return The code.
     */
    public int getCode() {
      return code;
    }

    /**
     * Sets the code.
     *
     * @param code The code to set.
     */
    public void setCode(int code) {
      this.code = code;
    }

    /**
     * Gets the message.
     *
     * @return The message.
     */
    public String getMessage() {
      return message;
    }

    /**
     * Sets the message.
     *
     * @param message The message to set.
     */
    public void setMessage(String message) {
      this.message = message;
    }

    /**
     * Gets the solution.
     *
     * @return The solution.
     */
    public String getSolution() {
      return solution;
    }

    /**
     * Sets the solution.
     *
     * @param solution The solution to set.
     */
    public void setSolution(String solution) {
      this.solution = solution;
    }

    /**
     * Gets the moreInfo.
     *
     * @return The moreInfo.
     */
    public String getMoreInfo() {
      return moreInfo;
    }

    /**
     * Sets the moreInfo.
     *
     * @param moreInfo The moreInfo to set.
     */
    public void setMoreInfo(String moreInfo) {
      this.moreInfo = moreInfo;
    }

  }

  /**
   * Gets the trace.
   *
   * @return The trace.
   */
  public String getTrace() {
    return trace;
  }

  /**
   * Sets the trace.
   *
   * @param trace The trace to set.
   */
  public void setTrace(String trace) {
    this.trace = trace;
  }

  /**
   * Gets the errors.
   *
   * @return The errors.
   */
  public List<QError> getErrors() {
    return errors;
  }

  /**
   * Sets the errors.
   *
   * @param errors The errors to set.
   */
  public void setErrors(List<QError> errors) {
    this.errors = errors;
  }


}
