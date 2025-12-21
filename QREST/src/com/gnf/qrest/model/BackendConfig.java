package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BackendConfig {

    @JsonProperty("allow_q_object")
    private boolean allowQObject;

    @JsonProperty("backend_name")
    private String backendName;

    @JsonProperty("backend_version")
    private String backendVersion;

    @JsonProperty("basis_gates")
    private List<String> basisGates;

    @JsonProperty("clops")
    private Object clops;

    @JsonProperty("clops_h")
    private int clopsH;

    @JsonProperty("clops_v")
    private Object clopsV;

    private boolean conditional;

    @JsonProperty("coords")
    private List<List<Integer>> coords;

    @JsonProperty("coupling_map")
    private List<List<Integer>> couplingMap;

    @JsonProperty("credits_required")
    private boolean creditsRequired;

    @JsonProperty("default_rep_delay")
    private int defaultRepDelay;

    private String description;
    
    private int dt;
    private int dtm;

    @JsonProperty("dynamic_reprate_enabled")
    private boolean dynamicRepRateEnabled;

    private List<GateConfig> gates;

    @JsonProperty("instruction_signatures")
    private List<Object> instructionSignatures;

    private boolean local;

    @JsonProperty("max_experiments")
    private int maxExperiments;

    @JsonProperty("max_shots")
    private int maxShots;

    @JsonProperty("meas_map")
    private List<List<Integer>> measMap;

    @JsonProperty("measure_esp_enabled")
    private boolean measureEspEnabled;

    private boolean memory;

    @JsonProperty("multi_meas_enabled")
    private boolean multiMeasEnabled;

    @JsonProperty("n_qubits")
    private int nQubits;

    @JsonProperty("n_registers")
    private int nRegisters;

    @JsonProperty("online_date")
    private String onlineDate;

    @JsonProperty("open_pulse")
    private boolean openPulse;

    @JsonProperty("parallel_compilation")
    private boolean parallelCompilation;

    @JsonProperty("processor_type")
    private ProcessorType processorType;

    @JsonProperty("quantum_volume")
    private Object quantumVolume;

    @JsonProperty("rep_delay_range")
    private List<Integer> repDelayRange;

    @JsonProperty("sample_name")
    private String sampleName;

    private boolean simulator;

    @JsonProperty("supported_features")
    private List<String> supportedFeatures;

    @JsonProperty("supported_instructions")
    private List<String> supportedInstructions;

    @JsonProperty("timing_constraints")
    private TimingConstraints timingConstraints;

    private Object url;

	public boolean isAllowQObject() {
		return allowQObject;
	}

	public void setAllowQObject(boolean allowQObject) {
		this.allowQObject = allowQObject;
	}

	public String getBackendName() {
		return backendName;
	}

	public void setBackendName(String backendName) {
		this.backendName = backendName;
	}

	public String getBackendVersion() {
		return backendVersion;
	}

	public void setBackendVersion(String backendVersion) {
		this.backendVersion = backendVersion;
	}

	public List<String> getBasisGates() {
		return basisGates;
	}

	public void setBasisGates(List<String> basisGates) {
		this.basisGates = basisGates;
	}

	public Object getClops() {
		return clops;
	}

	public void setClops(Object clops) {
		this.clops = clops;
	}

	public int getClopsH() {
		return clopsH;
	}

	public void setClopsH(int clopsH) {
		this.clopsH = clopsH;
	}

	public Object getClopsV() {
		return clopsV;
	}

	public void setClopsV(Object clopsV) {
		this.clopsV = clopsV;
	}

	public boolean isConditional() {
		return conditional;
	}

	public void setConditional(boolean conditional) {
		this.conditional = conditional;
	}

	public List<List<Integer>> getCoords() {
		return coords;
	}

	public void setCoords(List<List<Integer>> coords) {
		this.coords = coords;
	}

	public List<List<Integer>> getCouplingMap() {
		return couplingMap;
	}

	public void setCouplingMap(List<List<Integer>> couplingMap) {
		this.couplingMap = couplingMap;
	}

	public boolean isCreditsRequired() {
		return creditsRequired;
	}

	public void setCreditsRequired(boolean creditsRequired) {
		this.creditsRequired = creditsRequired;
	}

	public int getDefaultRepDelay() {
		return defaultRepDelay;
	}

	public void setDefaultRepDelay(int defaultRepDelay) {
		this.defaultRepDelay = defaultRepDelay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public int getDtm() {
		return dtm;
	}

	public void setDtm(int dtm) {
		this.dtm = dtm;
	}

	public boolean isDynamicRepRateEnabled() {
		return dynamicRepRateEnabled;
	}

	public void setDynamicRepRateEnabled(boolean dynamicRepRateEnabled) {
		this.dynamicRepRateEnabled = dynamicRepRateEnabled;
	}

	public List<GateConfig> getGates() {
		return gates;
	}

	public void setGates(List<GateConfig> gates) {
		this.gates = gates;
	}

	public List<Object> getInstructionSignatures() {
		return instructionSignatures;
	}

	public void setInstructionSignatures(List<Object> instructionSignatures) {
		this.instructionSignatures = instructionSignatures;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public int getMaxExperiments() {
		return maxExperiments;
	}

	public void setMaxExperiments(int maxExperiments) {
		this.maxExperiments = maxExperiments;
	}

	public int getMaxShots() {
		return maxShots;
	}

	public void setMaxShots(int maxShots) {
		this.maxShots = maxShots;
	}

	public List<List<Integer>> getMeasMap() {
		return measMap;
	}

	public void setMeasMap(List<List<Integer>> measMap) {
		this.measMap = measMap;
	}

	public boolean isMeasureEspEnabled() {
		return measureEspEnabled;
	}

	public void setMeasureEspEnabled(boolean measureEspEnabled) {
		this.measureEspEnabled = measureEspEnabled;
	}

	public boolean isMemory() {
		return memory;
	}

	public void setMemory(boolean memory) {
		this.memory = memory;
	}

	public boolean isMultiMeasEnabled() {
		return multiMeasEnabled;
	}

	public void setMultiMeasEnabled(boolean multiMeasEnabled) {
		this.multiMeasEnabled = multiMeasEnabled;
	}

	public int getnQubits() {
		return nQubits;
	}

	public void setnQubits(int nQubits) {
		this.nQubits = nQubits;
	}

	public int getnRegisters() {
		return nRegisters;
	}

	public void setnRegisters(int nRegisters) {
		this.nRegisters = nRegisters;
	}

	public String getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}

	public boolean isOpenPulse() {
		return openPulse;
	}

	public void setOpenPulse(boolean openPulse) {
		this.openPulse = openPulse;
	}

	public boolean isParallelCompilation() {
		return parallelCompilation;
	}

	public void setParallelCompilation(boolean parallelCompilation) {
		this.parallelCompilation = parallelCompilation;
	}

	public ProcessorType getProcessorType() {
		return processorType;
	}

	public void setProcessorType(ProcessorType processorType) {
		this.processorType = processorType;
	}

	public Object getQuantumVolume() {
		return quantumVolume;
	}

	public void setQuantumVolume(Object quantumVolume) {
		this.quantumVolume = quantumVolume;
	}

	public List<Integer> getRepDelayRange() {
		return repDelayRange;
	}

	public void setRepDelayRange(List<Integer> repDelayRange) {
		this.repDelayRange = repDelayRange;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public boolean isSimulator() {
		return simulator;
	}

	public void setSimulator(boolean simulator) {
		this.simulator = simulator;
	}

	public List<String> getSupportedFeatures() {
		return supportedFeatures;
	}

	public void setSupportedFeatures(List<String> supportedFeatures) {
		this.supportedFeatures = supportedFeatures;
	}

	public List<String> getSupportedInstructions() {
		return supportedInstructions;
	}

	public void setSupportedInstructions(List<String> supportedInstructions) {
		this.supportedInstructions = supportedInstructions;
	}

	public TimingConstraints getTimingConstraints() {
		return timingConstraints;
	}

	public void setTimingConstraints(TimingConstraints timingConstraints) {
		this.timingConstraints = timingConstraints;
	}

	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}
    
}