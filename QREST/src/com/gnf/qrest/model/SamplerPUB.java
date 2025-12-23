package com.gnf.qrest.model;

public class SamplerPUB extends PUB {

	private int shots;
	
	public SamplerPUB(Builder builder) {
		super(builder);
		this.shots = builder.shots;
	}
	
	public static class Builder extends PUB.Builder<Builder> {
		private int shots=16;
		
		public Builder shots(int shots) {
			this.shots = shots;
			return this;
		}
		
		@Override
		public SamplerPUB build() {
			return new SamplerPUB(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}
	
}
