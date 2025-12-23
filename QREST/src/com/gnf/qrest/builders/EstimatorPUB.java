package com.gnf.qrest.builders;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gnf.qrest.qiskit.Pauli;
import com.gnf.qrest.serializers.EstimatorPUBSerializer;

@JsonSerialize(using = EstimatorPUBSerializer.class)
public record EstimatorPUB ( 
	String circuit,
//	List<String> observables,
	
//	List<Pauli> observables,
//	@JsonSerialize(contentUsing = PauliListSerializer.class)
	List<List<Pauli>> observables,
//	SparsePauliOp observables,
	List<List<Double>> parameters,
	Double precision
) implements PUB {
	
	public static EstimatorPUB of(
			String circuit,
			List<List<Pauli>> observables,
			List<List<Double>> parameters,
			Double precision
			
	) {
		return new EstimatorPUB(
			circuit,
			observables,
			parameters,
			precision
		);
				
	}
		
	public static EstimatorPUB ofSingle(
			String circuit,
			List<Pauli> observable,
			List<List<Double>> parameters,
			Double precision
			
	) {
		return new EstimatorPUB(
			circuit,
			List.of(observable),
			parameters,
			precision
		);
					
	}
		
//	@Override
//	public List<Object> buildAsList() {
//		return List.of(circuit,
////			new PauliListWrapper(observables),
//			new PauliListWrapper(observables),
//			parameters,precision);
//	}

	public static Builder builder() {
        return new Builder();
    }

	public static class Builder {
		private String circuit;
//		private List<String> observables;
		
//		@JsonSerialize(contentUsing = PauliListSerializer.class)
		private List<List<Pauli>> observables;
		
//		private SparsePauliOp observables;
		private List<List<Double>> parameters = Collections.emptyList();
		private Double precision = 0.015625;
		
		public Builder circuit(String circuit) {
			this.circuit = circuit;
			return this;
		}

//		public Builder observables(List<String> observables) {
//			this.observables = observables;
//			return this;
//		}

		public Builder observable(List<Pauli> observable) {
			this.observables = List.of(observable);
			return this;
		}
		
		public Builder observables(List<List<Pauli>> observables) {
			this.observables = observables;
			return this;
		}
		
//		public Builder observables(SparsePauliOp observables) {
//			this.observables = observables;
//			return this;
//		}
		
		public Builder parameters(List<List<Double>> parameters) {
			this.parameters = parameters;
			return this;
		}
		
		public Builder precision(Double precision) {
			this.precision = precision;
			return this;
		}
		

		public EstimatorPUB build() {
			return EstimatorPUB.of(circuit,observables,parameters,precision);
		}

	}
	
}
