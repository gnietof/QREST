package com.gnf.qrest.qiskit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.QiskitRuntimeService;
import com.gnf.qrest.model.Program;
import com.gnf.qrest.model.QResponse;
import com.gnf.qrest.model.Usage;
import java.util.ArrayList;
import java.util.List;

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

  public static class State {
    private String status;
    private String reason;

    @JsonProperty("reason_code")
    private int reasonCode;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public int getReasonCode() {
      return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
      this.reasonCode = reasonCode;
    }

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void cancel() {
    QiskitRuntimeService service = QiskitRuntimeService.getInstance();

    service.cancelJob(id);
  }

  public String getBackend() {
    return backend;
  }

  public void setBackend(String backend) {
    this.backend = backend;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<String> getTags() {
    if (tags == null) {
      tags = new ArrayList<String>();
    }
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public Usage getUsage() {
    return usage;
  }

  public void setUsage(Usage usage) {
    this.usage = usage;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public boolean isDone() {
    boolean isDone = DONE_STATES.contains(status);
    return isDone;
  }

  public boolean isRunning() {
    boolean isRunning = !isInFinalState();
    return isRunning;
  }

  public boolean isError() {
    boolean isError = ERROR_STATES.contains(status);
    return isError;
  }

  public boolean isInFinalState() {
    boolean isFinal = FINAL_STATES.contains(status);
    return isFinal;
  }

  @Override
  public String toString() {
    String s = String.format("%s: %s [%s] (%s-%s) %s", getId(), getCreated(),
        getBackend(), getProgram().getId(), getStatus(), getTags().toString());
    return s;
  }

}
