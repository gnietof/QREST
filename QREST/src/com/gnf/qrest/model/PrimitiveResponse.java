package com.gnf.qrest.model;

/**
 * Models a PrimitiveResponse.
 */
public class PrimitiveResponse {
  private String id;
  private String backend;

  /**
   * Gets the id.
   *
   * @return The id.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id The id to set.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the backend.
   *
   * @return The backend.
   */
  public String getBackend() {
    return backend;
  }

  /**
   * Sets the backend.
   *
   * @param backend The backend to set.
   */
  public void setBackend(String backend) {
    this.backend = backend;
  }

}
