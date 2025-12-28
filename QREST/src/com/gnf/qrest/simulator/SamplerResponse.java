package com.gnf.qrest.simulator;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SamplerResponse {
	
	private Result result;
	
	public static class Result {

		@JsonProperty("quasi_dists")
		private List<Map<Integer,Double>> quasiDists;
	
		public List<Map<Integer, Double>> getQuasiDists() {
			return quasiDists;
		}
	
		public void setQuasiDist(List<Map<Integer, Double>> quasiDists) {
			this.quasiDists = quasiDists;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
