package com.gnf.qrest.builders;

public record BackendsRequest(
	String name, 
	Integer minNumQubits
) {
	
	public static Builder builder() {
        return new Builder();
    }

	public static class Builder {
		private String name;
		private Integer minNumQubits;
//		private Optional<String> name;
//		private Optional<Integer> minNumQubits;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder minNumQubits(Integer minNumQubits) {
			this.minNumQubits = minNumQubits;
			return this;
		}
		
		public BackendsRequest build() {
			return new BackendsRequest(name, minNumQubits);
		}
	}
	
}
