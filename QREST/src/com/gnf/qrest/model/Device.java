package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
	private String state;
	private String name;
	private BackendState status;
	
	@JsonProperty("is_simulator")
	private boolean isSimulator;

	private int qubits;

	@JsonProperty("queue_length")
	private int queueLength;
	
	@JsonProperty("processor_type")
	private ProcessorType processorType;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BackendState getStatus() {
		return status;
	}

	public void setStatus(BackendState status) {
		this.status = status;
	}

	public boolean isSimulator() {
		return isSimulator;
	}

	public void setSimulator(boolean isSimulator) {
		this.isSimulator = isSimulator;
	}

	public int getQubits() {
		return qubits;
	}

	public void setQubits(int qubits) {
		this.qubits = qubits;
	}

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public ProcessorType getProcessorType() {
		return processorType;
	}

	public void setProcessorType(ProcessorType processorType) {
		this.processorType = processorType;
	}

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	
	
}
