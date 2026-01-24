package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Models a Workload.
 */
public class Workload {

  private String id;

  @JsonProperty("user_id")
  private String userId;
  private String created;
  private String ended;
  private String backend;
  private String instance;

  @JsonProperty("accepting_jobs")
  private boolean acceptingJobs;
  private String mode;
  private String status;

  @JsonProperty("status_reason")
  private String statusReason;
  private List<String> tags;

  @JsonProperty("usage_seconds")
  private int usageSeconds;

  @Override
  public String toString() {
    String s = String.format("%s: %s (%s) [%s]", getId(), getCreated(), getStatus(), getMode());
    return s;
  }

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
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the userId.
   * 
   * @return The userId.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the userId.
   * 
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the created.
   * 
   * @return The created.
   */
  public String getCreated() {
    return created;
  }

  /**
   * Sets the created.
   * 
   * @param created the created to set
   */
  public void setCreated(String created) {
    this.created = created;
  }

  /**
   * Gets the ended.
   * 
   * @return The ended.
   */
  public String getEnded() {
    return ended;
  }

  /**
   * Sets the ended.
   * 
   * @param ended the ended to set
   */
  public void setEnded(String ended) {
    this.ended = ended;
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
   * @param backend the backend to set
   */
  public void setBackend(String backend) {
    this.backend = backend;
  }

  /**
   * Gets the instance.
   * 
   * @return The instance.
   */
  public String getInstance() {
    return instance;
  }

  /**
   * Sets the instance.
   * 
   * @param instance the instance to set
   */
  public void setInstance(String instance) {
    this.instance = instance;
  }

  /**
   * Gets the acceptingJobs.
   * 
   * @return The acceptingJobs.
   */
  public boolean isAcceptingJobs() {
    return acceptingJobs;
  }

  /**
   * Sets the acceptingJobs.
   * 
   * @param acceptingJobs the acceptingJobs to set
   */
  public void setAcceptingJobs(boolean acceptingJobs) {
    this.acceptingJobs = acceptingJobs;
  }

  /**
   * Gets the mode.
   * 
   * @return The mode.
   */
  public String getMode() {
    return mode;
  }

  /**
   * Sets the mode.
   * 
   * @param mode the mode to set
   */
  public void setMode(String mode) {
    this.mode = mode;
  }

  /**
   * Gets the status.
   * 
   * @return The status.
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status.
   * 
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the statusReason.
   * 
   * @return The statusReason.
   */
  public String getStatusReason() {
    return statusReason;
  }

  /**
   * Sets the statusReason.
   * 
   * @param statusReason the statusReason to set
   */
  public void setStatusReason(String statusReason) {
    this.statusReason = statusReason;
  }

  /**
   * Gets the tags.
   * 
   * @return The tags.
   */
  public List<String> getTags() {
    return tags;
  }

  /**
   * Sets the tags.
   * 
   * @param tags the tags to set
   */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  /**
   * Gets the usageSeconds.
   * 
   * @return The usageSeconds.
   */
  public int getUsageSeconds() {
    return usageSeconds;
  }

  /**
   * Sets the usageSeconds.
   * 
   * @param usageSeconds the usageSeconds to set
   */
  public void setUsageSeconds(int usageSeconds) {
    this.usageSeconds = usageSeconds;
  }

}
