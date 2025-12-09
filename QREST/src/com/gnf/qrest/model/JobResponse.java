package com.gnf.qrest.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobResponse {

    private String id;
    private String backend;
    private JobState state;
    private JobStatus status;
    private Map<String, Object> params;
    private Program program;
    private String created;
    private String runtime;
    private Integer cost;
    private List<String> tags;
    
	@JsonProperty("remote_storage")
    private RemoteStorage remoteStorage;
	@JsonProperty("session_id")
    private String sessionId;
	@JsonProperty("user_id")
    private String userId;
    private Usage usage;
    private Boolean isPrivate;
	@JsonProperty("estimated_running_time_seconds")
    private Double estimatedRunningTimeSeconds;
	@JsonProperty("calibration_id")
    private String calibrationId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}

	public JobState getState() {
		return state;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
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

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(Usage usage) {
		this.usage = usage;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Double getEstimated_running_time_seconds() {
		return estimatedRunningTimeSeconds;
	}

	public void setEstimated_running_time_seconds(Double estimated_running_time_seconds) {
		this.estimatedRunningTimeSeconds = estimated_running_time_seconds;
	}

	public RemoteStorage getRemoteStorage() {
		return remoteStorage;
	}

	public void setRemoteStorage(RemoteStorage remoteStorage) {
		this.remoteStorage = remoteStorage;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getEstimatedRunningTimeSeconds() {
		return estimatedRunningTimeSeconds;
	}

	public void setEstimatedRunningTimeSeconds(Double estimatedRunningTimeSeconds) {
		this.estimatedRunningTimeSeconds = estimatedRunningTimeSeconds;
	}

	public String getCalibrationId() {
		return calibrationId;
	}

	public void setCalibrationId(String calibrationId) {
		this.calibrationId = calibrationId;
	}

}
