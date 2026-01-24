package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class QResponse {

  private List<QError> errors;
  private String trace;

  public List<QError> getErrors() {
    return errors;
  }

  public static class QError {

    private int code;
    private String message;
    private String solution;

    @JsonProperty("more_info")
    private String moreInfo;

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public String getSolution() {
      return solution;
    }

    public void setSolution(String solution) {
      this.solution = solution;
    }

    public String getMoreInfo() {
      return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
      this.moreInfo = moreInfo;
    }
  }

  public void setErrors(List<QError> errors) {
    this.errors = errors;
  }

  public String getTrace() {
    return trace;
  }

  public void setTrace(String trace) {
    this.trace = trace;
  }

}
