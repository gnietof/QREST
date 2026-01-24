package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonProperty;

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBackendName() {
    return backendName;
  }

  public void setBackendName(String backendName) {
    this.backendName = backendName;
  }

  public String getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(String startedAt) {
    this.startedAt = startedAt;
  }

  public String getActivatedAt() {
    return activatedAt;
  }

  public void setActivatedAt(String activatedAt) {
    this.activatedAt = activatedAt;
  }

  public String getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(String closedAt) {
    this.closedAt = closedAt;
  }

  public String getLastJobStarted() {
    return lastJobStarted;
  }

  public void setLastJobStarted(String lastJobStarted) {
    this.lastJobStarted = lastJobStarted;
  }

  public String getLastJobCompleted() {
    return lastJobCompleted;
  }

  public void setLastJobCompleted(String lastJobCompleted) {
    this.lastJobCompleted = lastJobCompleted;
  }

  public int getInteractiveTTL() {
    return interactiveTTL;
  }

  public void setInteractiveTTL(int interactiveTTL) {
    this.interactiveTTL = interactiveTTL;
  }

  public int getMaxTTL() {
    return maxTTL;
  }

  public void setMaxTTL(int maxTTL) {
    this.maxTTL = maxTTL;
  }

  public int getActiveTTL() {
    return activeTTL;
  }

  public void setActiveTTL(int activeTTL) {
    this.activeTTL = activeTTL;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getStateReason() {
    return stateReason;
  }

  public void setStateReason(String stateReason) {
    this.stateReason = stateReason;
  }

  public boolean isAcceptingJobs() {
    return acceptingJobs;
  }

  public void setAcceptingJobs(boolean acceptingJobs) {
    this.acceptingJobs = acceptingJobs;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(int elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

}
