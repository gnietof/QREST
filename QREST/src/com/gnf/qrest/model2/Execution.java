package com.gnf.qrest.model2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Execution {
	@JsonProperty("init_qubits")
	private boolean initQubits = true;

	public boolean isInitQubits() {
		return initQubits;
	}

	public void setInitQubits(boolean initQubits) {
		this.initQubits = initQubits;
	}

}
