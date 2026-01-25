package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Models a Session
 */
public class Session {

  private String id;
  @JsonProperty("backend_name")
  private String backendName;

  @JsonProperty("started_at")
  private String startedAt;

  @JsonProperty("activated_at")
  private String activatedAt;

  @JsonProperty("closed_at")
  private String closedAt;

  @JsonProperty("last_job_started")
  private String lastJobStarted;

  @JsonProperty("last_job_completed")
  private String lastJobCompleted;

  @JsonProperty("interactive_ttl")
  private int interactiveTTL;

  @JsonProperty("max_ttl")
  private int maxTTL;

  @JsonProperty("active_ttl")
  private int activeTTL;
  private String state;

  @JsonProperty("state_reason")
  private String stateReason;

  @JsonProperty("accepting_jobs")
  private boolean acceptingJobs;
  private String mode;

  @JsonProperty("user_id")
  private String userId;

  @JsonProperty("elapsed_time")
  private int elapsedTime;

  /**
   * Gets the id.
   *
   *  @return The id.
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
   * Gets the backendName.
   *
   *  @return The backendName.
   */
  public String getBackendName() {
    return backendName;
  }

  /**
   * Sets the backendName.
   * 
   * @param backendName The backendName to set.
   */
  public void setBackendName(String backendName) {
    this.backendName = backendName;
  }

  /**
   * Gets the startedAt.
   *
   *  @return The startedAt.
   */
  public String getStartedAt() {
    return startedAt;
  }

  /**
   * Sets the startedAt.
   * 
   * @param startedAt The startedAt to set.
   */
  public void setStartedAt(String startedAt) {
    this.startedAt = startedAt;
  }

  /**
   * Gets the activatedAt.
   *
   *  @return The activatedAt.
   */
  public String getActivatedAt() {
    return activatedAt;
  }

  /**
   * Sets the activatedAt.
   * 
   * @param activatedAt The activatedAt to set.
   */
  public void setActivatedAt(String activatedAt) {
    this.activatedAt = activatedAt;
  }

  /**
   * Gets the closedAt.
   *
   *  @return The closedAt.
   */
  public String getClosedAt() {
    return closedAt;
  }

  /**
   * Sets the closedAt.
   * 
   * @param closedAt The closedAt to set.
   */
  public void setClosedAt(String closedAt) {
    this.closedAt = closedAt;
  }

  /**
   * Gets the lastJobStarted.
   *
   *  @return The lastJobStarted.
   */
  public String getLastJobStarted() {
    return lastJobStarted;
  }

  /**
   * Sets the lastJobStarted.
   * 
   * @param lastJobStarted The lastJobStarted to set.
   */
  public void setLastJobStarted(String lastJobStarted) {
    this.lastJobStarted = lastJobStarted;
  }

  /**
   * Gets the lastJobCompleted.
   *
   *  @return The lastJobCompleted.
   */
  public String getLastJobCompleted() {
    return lastJobCompleted;
  }

  /**
   * Sets the lastJobCompleted.
   * 
   * @param lastJobCompleted The lastJobCompleted to set.
   */
  public void setLastJobCompleted(String lastJobCompleted) {
    this.lastJobCompleted = lastJobCompleted;
  }

  /**
   * Gets the interactiveTTL.
   *
   *  @return The interactiveTTL.
   */
  public int getInteractiveTTL() {
    return interactiveTTL;
  }

  /**
   * Sets the interactiveTTL.
   * 
   * @param interactiveTTL The interactiveTTL to set.
   */
  public void setInteractiveTTL(int interactiveTTL) {
    this.interactiveTTL = interactiveTTL;
  }

  /**
   * Gets the maxTTL.
   *
   *  @return The maxTTL.
   */
  public int getMaxTTL() {
    return maxTTL;
  }

  /**
   * Sets the maxTTL.
   * 
   * @param maxTTL The maxTTL to set.
   */
  public void setMaxTTL(int maxTTL) {
    this.maxTTL = maxTTL;
  }

  /**
   * Gets the activeTTL.
   *
   *  @return The activeTTL.
   */
  public int getActiveTTL() {
    return activeTTL;
  }

  /**
   * Sets the activeTTL.
   * 
   * @param activeTTL The activeTTL to set.
   */
  public void setActiveTTL(int activeTTL) {
    this.activeTTL = activeTTL;
  }

  /**
   * Gets the state.
   *
   *  @return The state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   * 
   * @param state The state to set.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the stateReason.
   *
   *  @return The stateReason.
   */
  public String getStateReason() {
    return stateReason;
  }

  /**
   * Sets the stateReason.
   * 
   * @param stateReason The stateReason to set.
   */
  public void setStateReason(String stateReason) {
    this.stateReason = stateReason;
  }

  /**
   * Gets the acceptingJobs.
   *
   *  @return The acceptingJobs.
   */
  public boolean isAcceptingJobs() {
    return acceptingJobs;
  }

  /**
   * Sets the acceptingJobs.
   * 
   * @param acceptingJobs The acceptingJobs to set.
   */
  public void setAcceptingJobs(boolean acceptingJobs) {
    this.acceptingJobs = acceptingJobs;
  }

  /**
   * Gets the mode.
   *
   *  @return The mode.
   */
  public String getMode() {
    return mode;
  }

  /**
   * Sets the mode.
   * 
   * @param mode The mode to set.
   */
  public void setMode(String mode) {
    this.mode = mode;
  }

  /**
   * Gets the userId.
   *
   *  @return The userId.
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Sets the userId.
   * 
   * @param userId The userId to set.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the elapsedTime.
   *
   *  @return The elapsedTime.
   */
  public int getElapsedTime() {
    return elapsedTime;
  }

  /**
   * Sets the elapsedTime.
   * 
   * @param elapsedTime The elapsedTime to set.
   */
  public void setElapsedTime(int elapsedTime) {
    this.elapsedTime = elapsedTime;
  }


}
