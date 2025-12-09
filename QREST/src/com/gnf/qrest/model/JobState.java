package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobState {
    private JobStateStatus status;
    private String reason;
    @JsonProperty("reason_code")
    private Integer reasonCode;
    @JsonProperty("reason_solution")
    private String reasonSolution;

	public JobStateStatus getStatus() {
		return status;
	}

	public void setStatus(JobStateStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReasonSolution() {
		return reasonSolution;
	}

	public void setReasonSolution(String reasonSolution) {
		this.reasonSolution = reasonSolution;
	}


}
