package com.gnf.qrest.model;

import java.util.List;

public class JobsListResponse {

    private List<JobResponse> jobs;
    private Integer count;
    private Integer offset;
    private Integer limit;

	public List<JobResponse> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobResponse> jobs) {
		this.jobs = jobs;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
