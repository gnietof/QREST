package com.gnf.qrest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobResults {

	private List<Result> results;
	private Metadata metadata;
	
	public static class Result {
		private Data data;
		private Metadata metadadata;
		
		public static class Data {
			private C c;
			private List<Double> evs;
			private List<Double> stds;
			@JsonProperty("ensemble_standard_error")
			private List<Double> ensembleStandardError;
			
			public static class C {
				private List<String> samples;
				@JsonProperty("num_bits")
				private int numBits;
				
				public List<String> getSamples() {
					return samples;
				}
				
				public void setSamples(List<String> samples) {
					this.samples = samples;
				}
				
			}
			
			public C getC() {
				return c;
			}

			public void setC(C c) {
				this.c = c;
			}

			public List<Double> getEvs() {
				return evs;
			}

			public void setEvs(List<Double> evs) {
				this.evs = evs;
			}

			public List<Double> getStds() {
				return stds;
			}

			public void setStds(List<Double> stds) {
				this.stds = stds;
			}

			public List<Double> getEnsemble_standard_error() {
				return ensembleStandardError;
			}

			public void setEnsemble_standard_error(List<Double> ensemble_standard_error) {
				this.ensembleStandardError = ensemble_standard_error;
			}
		}

		public static class Metadata {
			private int shots;
			@JsonProperty("target_precision")
			private double targetPrecision;
			@JsonProperty("num_ranzomizations")
			private int numRanzomizations;
			@JsonProperty("circuit_metadata")
			private CircuitMetadata circuitMetadata;
			private Resilience resilience;
			
			public static class CircuitMetadata {
				
			}

			public static class Resilience {
				
			}

			public int getShots() {
				return shots;
			}

			public void setShots(int shots) {
				this.shots = shots;
			}

			public Resilience getResilience() {
				return resilience;
			}

			public void setResilience(Resilience resilience) {
				this.resilience = resilience;
			}

			public double getTargetPrecision() {
				return targetPrecision;
			}

			public void setTargetPrecision(double targetPrecision) {
				this.targetPrecision = targetPrecision;
			}

			public int getNumRanzomizations() {
				return numRanzomizations;
			}

			public void setNumRanzomizations(int numRanzomizations) {
				this.numRanzomizations = numRanzomizations;
			}

			public CircuitMetadata getCircuitMetadata() {
				return circuitMetadata;
			}

			public void setCircuitMetadata(CircuitMetadata circuitMetadata) {
				this.circuitMetadata = circuitMetadata;
			}
		}

		public Data getData() {
			return data;
		}

		public void setData(Data data) {
			this.data = data;
		}

		public Metadata getMetadadata() {
			return metadadata;
		}

		public void setMetadadata(Metadata metadadata) {
			this.metadadata = metadadata;
		}

	}

	public static class Metadata {
		@JsonProperty("dynamical_decoupling")
		private DynamicalDecoupling dynamicalDecoupling;
		private Twirling twirling;
		private Resilience resilience;
		private int version = 2;
		
		public static class DynamicalDecoupling {
			private boolean enable;
			@JsonProperty("sequence_type")
			private String sequenceType;
			@JsonProperty("extra_slack_distribution")
			private String extraSlackDistribution;
			@JsonProperty("scheduling_method")
			private String schedulingMethod;

			public boolean isEnable() {
				return enable;
			}

			public void setEnable(boolean enable) {
				this.enable = enable;
			}

			public String getSequenceType() {
				return sequenceType;
			}

			public void setSequenceType(String sequenceType) {
				this.sequenceType = sequenceType;
			}

			public String getExtraSlackDistribution() {
				return extraSlackDistribution;
			}

			public void setExtraSlackDistribution(String extraSlackDistribution) {
				this.extraSlackDistribution = extraSlackDistribution;
			}

			public String getSchedulingMethod() {
				return schedulingMethod;
			}

			public void setSchedulingMethod(String schedulingMethod) {
				this.schedulingMethod = schedulingMethod;
			}

		}
		
		public static class Twirling {
			@JsonProperty("enable_gates")
			private boolean enableGates;
			@JsonProperty("enable_measure")
			private boolean enableMeasure;
			@JsonProperty("num_randomizations")
			private String numRandomizations;
			@JsonProperty("shots_per_randomizations")
			private String shotsPerRandomizations;
			@JsonProperty("interleave_randomizations")
			private boolean interleaveRandomizations;
			private String strategy;

			public String getStrategy() {
				return strategy;
			}

			public void setStrategy(String strategy) {
				this.strategy = strategy;
			}

			public boolean isEnableGates() {
				return enableGates;
			}

			public void setEnableGates(boolean enableGates) {
				this.enableGates = enableGates;
			}

			public boolean isEnableMeasure() {
				return enableMeasure;
			}

			public void setEnableMeasure(boolean enableMeasure) {
				this.enableMeasure = enableMeasure;
			}

			public String getNumRandomizations() {
				return numRandomizations;
			}

			public void setNumRandomizations(String numRandomizations) {
				this.numRandomizations = numRandomizations;
			}

			public String getShotsPerRandomizations() {
				return shotsPerRandomizations;
			}

			public void setShotsPerRandomizations(String shotsPerRandomizations) {
				this.shotsPerRandomizations = shotsPerRandomizations;
			}

			public boolean isInterleaveRandomizations() {
				return interleaveRandomizations;
			}

			public void setInterleaveRandomizations(boolean interleaveRandomizations) {
				this.interleaveRandomizations = interleaveRandomizations;
			}
			
		}
		
		public static class Resilience {
			@JsonProperty("measure_mitigation")
			public boolean measureMitigation;
			@JsonProperty("zne_mitigation")
			public boolean ZNEMitigation;
			@JsonProperty("pec_mitigation")
			public boolean PECMitigation;

			public boolean isMeasureMitigation() {
				return measureMitigation;
			}

			public void setMeasureMitigation(boolean measureMitigation) {
				this.measureMitigation = measureMitigation;
			}

			public boolean isZNEMitigation() {
				return ZNEMitigation;
			}

			public void setZNEMitigation(boolean zNEMitigation) {
				ZNEMitigation = zNEMitigation;
			}

			public boolean isPECMitigation() {
				return PECMitigation;
			}

			public void setPECMitigation(boolean pECMitigation) {
				PECMitigation = pECMitigation;
			}


		}

		public static class Execution {
			public static class ExecutionSpans {
			}
		}

		public DynamicalDecoupling getDynamical_decoupling() {
			return dynamicalDecoupling;
		}

		public void setDynamical_decoupling(DynamicalDecoupling dynamical_decoupling) {
			this.dynamicalDecoupling = dynamical_decoupling;
		}

		public Twirling getTwirling() {
			return twirling;
		}

		public void setTwirling(Twirling twirling) {
			this.twirling = twirling;
		}

		public Resilience getResilience() {
			return resilience;
		}

		public void setResilience(Resilience resilience) {
			this.resilience = resilience;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}
		
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}
