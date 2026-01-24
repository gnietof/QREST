package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


/**
 * Models a Workloads.
 */
public class Workloads {

  private List<Workload> workloads;

  @JsonProperty("total_count")
  private int totalCount;
  private int limit;
  private Next next;

  /**
   * Models a Next. 
   */
  public static class Next {
    private String href;

    /**
     * Gets the href.
     * 
     * @return The href.
     */
    public String getHref() {
      return href;
    }

    /**
     * Sets the href.
     * 
     * @param href the href to set
     */
    public void setHref(String href) {
      this.href = href;
    }


  }

  /**
   * Gets the workloads.
   * 
   * @return The workloads.
   */
  public List<Workload> getWorkloads() {
    return workloads;
  }

  /**
   * Sets the workloads.
   * 
   * @param workloads the workloads to set
   */
  public void setWorkloads(List<Workload> workloads) {
    this.workloads = workloads;
  }

  /**
   * Gets the totalCount.
   * 
   * @return The totalCount.
   */
  public int getTotalCount() {
    return totalCount;
  }

  /**
   * Sets the totalCount.
   * 
   * @param totalCount the totalCount to set
   */
  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  /**
   * Gets the limit.
   * 
   * @return The limit.
   */
  public int getLimit() {
    return limit;
  }

  /**
   * Sets the limit.
   * 
   * @param limit the limit to set
   */
  public void setLimit(int limit) {
    this.limit = limit;
  }

  /**
   * Gets the next.
   * 
   * @return The next.
   */
  public Next getNext() {
    return next;
  }

  /**
   * Sets the next.
   * 
   * @param next the next to set
   */
  public void setNext(Next next) {
    this.next = next;
  }

}
