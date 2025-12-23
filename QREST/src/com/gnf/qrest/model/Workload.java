package com.gnf.qrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEnded() {
		return ended;
	}

	public void setEnded(String ended) {
		this.ended = ended;
	}

	public String getBackend() {
		return backend;
	}

	public void setBackend(String backend) {
		this.backend = backend;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public int getUsageSeconds() {
		return usageSeconds;
	}

	public void setUsageSeconds(int usageSeconds) {
		this.usageSeconds = usageSeconds;
	}
	
	@Override
	public String toString() {
		String s = String.format("%s: %s (%s) [%s]",getId(),getCreated(),getStatus(),getMode());
		return s;
	}
	
}
