package com.gnf.qrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Workloads {

	private List<Workload> workloads;
	
	@JsonProperty("total_count")
	private int totalCount;
	private int limit;
	private Next next;
	
	public static class Next {
		private String href;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}
		
	}

	public List<Workload> getWorkloads() {
		return workloads;
	}

	public void setWorkloads(List<Workload> workloads) {
		this.workloads = workloads;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Next getNext() {
		return next;
	}

	public void setNext(Next next) {
		this.next = next;
	}
	
}
