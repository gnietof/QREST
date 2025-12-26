package com.gnf.qrest.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gnf.qrest.qiskit.SparsePauliOp;
import com.gnf.qrest.serializers.EstimatorPUBSerializer;

@JsonSerialize(using = EstimatorPUBSerializer.class)
public class EstimatorPUB extends PUB { 
	private List<Paulis> observables;
	private double precision;
	
	public EstimatorPUB(Builder builder) {
		super(builder);
		this.observables = builder.observables;
		this.precision = builder.precision;
	}
	
	public static class Builder extends PUB.Builder<Builder> {
		private List<Paulis> observables;
		private double precision = 0.015625;
		
		public Builder precision(double precision) {
			this.precision = precision;
			return this;
		}
		
		public Builder observables(List<Paulis> observables) {
			this.observables = observables;
			return this;
		}
		
		public Builder observable(Paulis observable) {
			this.observables = List.of(observable);
			return this;
		}
		
//		public Builder observables(List<SparsePauliOp> observables) {
//			this.observables = observables.stream().map(SparsePauliOp::getPaulis).collect(Collectors.toList());
//			return this;
//		}
		
		public Builder observable(SparsePauliOp observable) {
			this.observables = List.of(observable.getPaulis());
			return this;
		}
		
		@Override
		public EstimatorPUB build() {
			return new EstimatorPUB(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
		
	}

	public List<Paulis> getObservables() {
		return observables;
	}

	public void setObservables(List<Paulis> observables) {
		this.observables = observables;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}
		
//	@Override
//	public List<Object> buildAsList() {
//		return List.of(circuit,
////			new PauliListWrapper(observables),
//			new PauliListWrapper(observables),
//			parameters,precision);
//	}

//	public static Builder builder() {
//        return new Builder();
//    }

//	public static class Builder {
//		private String circuit;
////		private List<String> observables;
//		
////		@JsonSerialize(contentUsing = PauliListSerializer.class)
//		private List<Paulis> observables;
//		
////		private SparsePauliOp observables;
//		private List<List<Double>> parameters = Collections.emptyList();
//		private Double precision = 0.015625;
//		
//		public Builder circuit(String circuit) {
//			this.circuit = circuit;
//			return this;
//		}
//
////		public Builder observables(List<String> observables) {
////			this.observables = observables;
////			return this;
////		}
//
//		public Builder observable(Paulis observable) {
//			this.observables = List.of(observable);
//			return this;
//		}
//		
//		public Builder observables(List<Paulis> observables) {
//			this.observables = observables;
//			return this;
//		}
//		
////		public Builder observables(SparsePauliOp observables) {
////			this.observables = observables;
////			return this;
////		}
//		
//		public Builder parameters(List<List<Double>> parameters) {
//			this.parameters = parameters;
//			return this;
//		}
//		
//		public Builder precision(Double precision) {
//			this.precision = precision;
//			return this;
//		}
//		
//
//		public EstimatorPUB build() {
//			return EstimatorPUB.of(circuit,observables,parameters,precision);
//		}
//
//	}
	
}
