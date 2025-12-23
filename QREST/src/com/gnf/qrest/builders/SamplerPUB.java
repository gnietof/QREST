package com.gnf.qrest.builders;

import java.util.Collections;
import java.util.List;

public record SamplerPUB (

	String circuit,
	List<List<Double>> parameters,
	int shots
) implements PUB {
	
	public static Builder builder() {
        return new Builder();
    }

//	@Override
//	public List<Object> buildAsList() {
//		return List.of(circuit,parameters,shots);
//	}

	public static class Builder {
		private String circuit;
		private List<List<Double>> parameters = Collections.emptyList();
		private int shots=16;
		
		public Builder circuit(String circuit) {
			this.circuit = circuit;
			return this;
		}

		public Builder parameters(List<List<Double>> parameters) {
			this.parameters = parameters;
			return this;
		}
		
		public Builder shots(int shots) {
			this.shots = shots;
			return this;
		}
		
		public SamplerPUB build() {
			return new SamplerPUB(circuit,parameters,shots);
		}
	}
	
}
