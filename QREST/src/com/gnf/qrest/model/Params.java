package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Models a Params.
 */
public class Params {
  private List<? extends PUB> pubs;
  private Options options;
  private int version = 2;

  @JsonProperty("support_qiskit")
  private boolean supportQiskit = false;

  /**
   * Gets the pubs.
   *
   *  @return the pubs
   */
  public List<? extends PUB> getPubs() {
    return pubs;
  }

  /**
   * Sets the pubs.
   * 
   * @param pubs the pubs to set
   */
  public void setPubs(List<? extends PUB> pubs) {
    this.pubs = pubs;
  }

  /**
   * Gets the options.
   *
   *  @return the options
   */
  public Options getOptions() {
    if (options == null) {
      options = new Options();
    }
    return options;
  }

  /**
   * Sets the options.
   * 
   * @param options the options to set
   */
  public void setOptions(Options options) {
    this.options = options;
  }

  /**
   * Gets the version.
   *
   *  @return the version
   */
  public int getVersion() {
    return version;
  }

  /**
   * Sets the version.
   * 
   * @param version the version to set
   */
  public void setVersion(int version) {
    this.version = version;
  }

  /**
   * Gets the supportQiskit.
   *
   *  @return the supportQiskit
   */
  public boolean isSupportQiskit() {
    return supportQiskit;
  }

  /**
   * Sets the supportQiskit.
   * 
   * @param supportQiskit the supportQiskit to set
   */
  public void setSupportQiskit(boolean supportQiskit) {
    this.supportQiskit = supportQiskit;
  }



}
