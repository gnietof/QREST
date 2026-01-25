package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.model.Program;
import com.gnf.qrest.model.QResponse;
import com.gnf.qrest.model.Usage;
import java.util.List;

/**
 * Models a QResponse.
 */
public class Job extends QResponse {

  private static List<String> FINAL_STATES = List.of("Cancelled", "Failed", "Completed");
  private static List<String> DONE_STATES = List.of("Completed");
  private static List<String> ERROR_STATES = List.of("Failed");

  private String id;
  private String backend;
  private State state;

  @JsonProperty("user_id")
  private String userId;

  private Program program;
  private String created;
  private Usage usage;

  @JsonProperty("session_id")
  private String sessionId;

  private String status;
  private List<String> tags;

  /**
   * Models a State.
   */
  public static class State {
    private String status;
    private String reason;

    @JsonProperty("reason_code")
    private int reasonCode;

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
     * @param status The status to set.
     */
    public void setStatus(String status) {
      this.status = status;
    }

    /**
     * Gets the reason.
     *
     * @return The reason.
     */
    public String getReason() {
      return reason;
    }

    /**
     * Sets the reason.
     *
     * @param reason The reason to set.
     */
    public void setReason(String reason) {
      this.reason = reason;
    }

    /**
     * Gets the reasonCode.
     *
     * @return The reasonCode.
     */
    public int getReasonCode() {
      return reasonCode;
    }

    /**
     * Sets the reasonCode.
     *
     * @param reasonCode The reasonCode to set.
     */
    public void setReasonCode(int reasonCode) {
      this.reasonCode = reasonCode;
    }

  }

  /**
   * Cancels a job.
   */
  public void cancel() {
    QiskitRuntimeService service = QiskitRuntimeService.getInstance();

    service.cancelJob(id);
  }

  /**
   * Checks if this job is in an completed state.
   *
   * @return Whether this job is in a completed state.
   */
  public boolean isDone() {
    boolean isDone = DONE_STATES.contains(status);
    return isDone;
  }

  /**
   * Checks if this job is in an running state.
   *
   * @return Whether this job is in a running state.
   */
  public boolean isRunning() {
    boolean isRunning = !isInFinalState();
    return isRunning;
  }

  /**
   * Checks if this job is in an error state.
   *
   * @return Whether this job is in an error state.
   */
  public boolean isError() {
    boolean isError = ERROR_STATES.contains(status);
    return isError;
  }

  /**
   * Checks if this job is in an final state.
   *
   * @return Whether this job is in an final state.
   */
  public boolean isInFinalState() {
    boolean isFinal = FINAL_STATES.contains(status);
    return isFinal;
  }

  @Override
  public String toString() {
    String s = String.format("%s: %s [%s] (%s-%s) %s", getId(), getCreated(), getBackend(),
        getProgram().getId(), getStatus(), getTags().toString());
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

  /**
   * Gets the state.
   *
   * @return The state.
   */
  public State getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state The state to set.
   */
  public void setState(State state) {
    this.state = state;
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
   * @param userId The userId to set.
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Gets the program.
   *
   * @return The program.
   */
  public Program getProgram() {
    return program;
  }

  /**
   * Sets the program.
   *
   * @param program The program to set.
   */
  public void setProgram(Program program) {
    this.program = program;
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
   * @param created The created to set.
   */
  public void setCreated(String created) {
    this.created = created;
  }

  /**
   * Gets the usage.
   *
   * @return The usage.
   */
  public Usage getUsage() {
    return usage;
  }

  /**
   * Sets the usage.
   *
   * @param usage The usage to set.
   */
  public void setUsage(Usage usage) {
    this.usage = usage;
  }

  /**
   * Gets the sessionId.
   *
   * @return The sessionId.
   */
  public String getSessionId() {
    return sessionId;
  }

  /**
   * Sets the sessionId.
   *
   * @param sessionId The sessionId to set.
   */
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
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
   * @param status The status to set.
   */
  public void setStatus(String status) {
    this.status = status;
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
   * @param tags The tags to set.
   */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

}
