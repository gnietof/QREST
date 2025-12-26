package com.gnf.qrest.model;

import java.util.List;

public class Tags {

	private List<String> tags;
	
	public Tags() {
	}
	
	public Tags(List<String> tags) {
		this.tags = tags;
	}

	public Tags(String... tags) {
		this.tags = List.of(tags);
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
