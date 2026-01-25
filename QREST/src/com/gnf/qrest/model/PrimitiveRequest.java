package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * PrimitiveRequest abstract class
 */
public abstract class PrimitiveRequest {
  @JsonProperty("program_id")
  private String programId;
  private Params params;
  private String backend;

  /**
   * Constructor from backend, PUB's and program.
   * 
   * @param backend The backend.
   * @param pubs The PUBs.
   * @param programId The programId.
   */
  public PrimitiveRequest(String backend, List<? extends PUB> pubs, String programId) {
    this.backend = backend;
    this.programId = programId;
    this.params = new Params();
    params.setPubs(pubs);
  }

  /**
   * Gets the programId.
   *
   * @return the programId
   */
  public String getProgramId() {
    return programId;
  }

  /**
   * Sets the programId.
   * 
   * @param programId the programId to set
   */
  public void setProgramId(String programId) {
    this.programId = programId;
  }

  /**
   * Gets the params.
   *
   * @return the params
   */
  public Params getParams() {
    return params;
  }

  /**
   * Sets the params.
   * 
   * @param params the params to set
   */
  public void setParams(Params params) {
    this.params = params;
  }

  /**
   * Gets the backend.
   *
   * @return the backend
   */
  public String getBackend() {
    return backend;
  }

  /**
   * Sets the backend.
   * 
   * @param backend the backend to set
   */
  public void setBackend(String backend) {
    this.backend = backend;
  }

}
