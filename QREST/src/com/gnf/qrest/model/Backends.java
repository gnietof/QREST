package com.gnf.qrest.model;

import java.util.List;


/**
 * Models the list of backends available. 
 */
public class Backends {

  private List<Backend> devices;

  /**
   * Gets the devices.
   * 
   * @return The devices.
   */
  public List<Backend> getDevices() {
    return devices;
  }

  /**
   * Sets the devices.
   * 
   * @param devices the devices to set.
   */
  public void setDevices(List<Backend> devices) {
    this.devices = devices;
  }


}
