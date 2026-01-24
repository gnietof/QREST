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

  /**
   * Gets the allowQObject.
   * 
   * @return The allowQObject.
   */
  public boolean isAllowQObject() {
    return allowQObject;
  }

  /**
   * Sets the allowQObject.
   * 
   * @param allowQObject the allowQObject to set.
   */
  public void setAllowQObject(boolean allowQObject) {
    this.allowQObject = allowQObject;
  }

  /**
   * Gets the backendName.
   * 
   * @return The backendName.
   */
  public String getBackendName() {
    return backendName;
  }

  /**
   * Sets the backendName.
   * 
   * @param backendName the backendName to set.
   */
  public void setBackendName(String backendName) {
    this.backendName = backendName;
  }

  /**
   * Gets the backendVersion.
   * 
   * @return The backendVersion.
   */
  public String getBackendVersion() {
    return backendVersion;
  }

  /**
   * Sets the backendVersion.
   * 
   * @param backendVersion the backendVersion to set.
   */
  public void setBackendVersion(String backendVersion) {
    this.backendVersion = backendVersion;
  }

  /**
   * Gets the basisGates.
   * 
   * @return The basisGates.
   */
  public List<String> getBasisGates() {
    return basisGates;
  }

  /**
   * Sets the basisGates.
   * 
   * @param basisGates the basisGates to set.
   */
  public void setBasisGates(List<String> basisGates) {
    this.basisGates = basisGates;
  }

  /**
   * Gets the clops.
   * 
   * @return The clops.
   */
  public Object getClops() {
    return clops;
  }

  /**
   * Sets the clops.
   * 
   * @param clops the clops to set.
   */
  public void setClops(Object clops) {
    this.clops = clops;
  }

  /**
   * Gets the clopsH.
   * 
   * @return The clopsH.
   */
  public int getClopsH() {
    return clopsH;
  }

  /**
   * Sets the clopsH.
   * 
   * @param clopsH the clopsH to set.
   */
  public void setClopsH(int clopsH) {
    this.clopsH = clopsH;
  }

  /**
   * Gets the clopsV.
   * 
   * @return The clopsV.
   */
  public Object getClopsV() {
    return clopsV;
  }

  /**
   * Sets the clopsV.
   * 
   * @param clopsV the clopsV to set.
   */
  public void setClopsV(Object clopsV) {
    this.clopsV = clopsV;
  }

  /**
   * Gets the conditional.
   * 
   * @return The conditional.
   */
  public boolean isConditional() {
    return conditional;
  }

  /**
   * Sets the conditional.
   * 
   * @param conditional the conditional to set.
   */
  public void setConditional(boolean conditional) {
    this.conditional = conditional;
  }

  /**
   * Gets the coords.
   * 
   * @return The coords.
   */
  public List<List<Integer>> getCoords() {
    return coords;
  }

  /**
   * Sets the coords.
   * 
   * @param coords the coords to set.
   */
  public void setCoords(List<List<Integer>> coords) {
    this.coords = coords;
  }

  /**
   * Gets the couplingMap.
   * 
   * @return The couplingMap.
   */
  public List<List<Integer>> getCouplingMap() {
    return couplingMap;
  }

  /**
   * Sets the couplingMap.
   * 
   * @param couplingMap the couplingMap to set.
   */
  public void setCouplingMap(List<List<Integer>> couplingMap) {
    this.couplingMap = couplingMap;
  }

  /**
   * Gets the creditsRequired.
   * 
   * @return The creditsRequired.
   */
  public boolean isCreditsRequired() {
    return creditsRequired;
  }

  /**
   * Sets the creditsRequired.
   * 
   * @param creditsRequired the creditsRequired to set.
   */
  public void setCreditsRequired(boolean creditsRequired) {
    this.creditsRequired = creditsRequired;
  }

  /**
   * Gets the defaultRepDelay.
   * 
   * @return The defaultRepDelay.
   */
  public int getDefaultRepDelay() {
    return defaultRepDelay;
  }

  /**
   * Sets the defaultRepDelay.
   * 
   * @param defaultRepDelay the defaultRepDelay to set.
   */
  public void setDefaultRepDelay(int defaultRepDelay) {
    this.defaultRepDelay = defaultRepDelay;
  }

  /**
   * Gets the description.
   * 
   * @return The description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   * 
   * @param description the description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the dt.
   * 
   * @return The dt.
   */
  public int getDt() {
    return dt;
  }

  /**
   * Sets the dt.
   * 
   * @param dt the dt to set.
   */
  public void setDt(int dt) {
    this.dt = dt;
  }

  /**
   * Gets the dtm.
   * 
   * @return The dtm.
   */
  public int getDtm() {
    return dtm;
  }

  /**
   * Sets the dtm.
   * 
   * @param dtm the dtm to set.
   */
  public void setDtm(int dtm) {
    this.dtm = dtm;
  }

  /**
   * Gets the dynamicRepRateEnabled.
   * 
   * @return The dynamicRepRateEnabled.
   */
  public boolean isDynamicRepRateEnabled() {
    return dynamicRepRateEnabled;
  }

  /**
   * Sets the dynamicRepRateEnabled.
   * 
   * @param dynamicRepRateEnabled the dynamicRepRateEnabled to set.
   */
  public void setDynamicRepRateEnabled(boolean dynamicRepRateEnabled) {
    this.dynamicRepRateEnabled = dynamicRepRateEnabled;
  }

  /**
   * Gets the gates.
   * 
   * @return The gates.
   */
  public List<GateConfig> getGates() {
    return gates;
  }

  /**
   * Sets the gates.
   * 
   * @param gates the gates to set.
   */
  public void setGates(List<GateConfig> gates) {
    this.gates = gates;
  }

  /**
   * Gets the instructionSignatures.
   * 
   * @return The instructionSignatures.
   */
  public List<Object> getInstructionSignatures() {
    return instructionSignatures;
  }

  /**
   * Sets the instructionSignatures.
   * 
   * @param instructionSignatures the instructionSignatures to set.
   */
  public void setInstructionSignatures(List<Object> instructionSignatures) {
    this.instructionSignatures = instructionSignatures;
  }

  /**
   * Gets the local.
   * 
   * @return The local.
   */
  public boolean isLocal() {
    return local;
  }

  /**
   * Sets the local.
   * 
   * @param local the local to set.
   */
  public void setLocal(boolean local) {
    this.local = local;
  }

  /**
   * Gets the maxExperiments.
   * 
   * @return The maxExperiments.
   */
  public int getMaxExperiments() {
    return maxExperiments;
  }

  /**
   * Sets the maxExperiments.
   * 
   * @param maxExperiments the maxExperiments to set.
   */
  public void setMaxExperiments(int maxExperiments) {
    this.maxExperiments = maxExperiments;
  }

  /**
   * Gets the maxShots.
   * 
   * @return The maxShots.
   */
  public int getMaxShots() {
    return maxShots;
  }

  /**
   * Sets the maxShots.
   * 
   * @param maxShots the maxShots to set.
   */
  public void setMaxShots(int maxShots) {
    this.maxShots = maxShots;
  }

  /**
   * Gets the measMap.
   * 
   * @return The measMap.
   */
  public List<List<Integer>> getMeasMap() {
    return measMap;
  }

  /**
   * Sets the measMap.
   * 
   * @param measMap the measMap to set.
   */
  public void setMeasMap(List<List<Integer>> measMap) {
    this.measMap = measMap;
  }

  /**
   * Gets the measureEspEnabled.
   * 
   * @return The measureEspEnabled.
   */
  public boolean isMeasureEspEnabled() {
    return measureEspEnabled;
  }

  /**
   * Sets the measureEspEnabled.
   * 
   * @param measureEspEnabled the measureEspEnabled to set.
   */
  public void setMeasureEspEnabled(boolean measureEspEnabled) {
    this.measureEspEnabled = measureEspEnabled;
  }

  /**
   * Gets the memory.
   * 
   * @return The memory.
   */
  public boolean isMemory() {
    return memory;
  }

  /**
   * Sets the memory.
   * 
   * @param memory the memory to set.
   */
  public void setMemory(boolean memory) {
    this.memory = memory;
  }

  /**
   * Gets the multiMeasEnabled.
   * 
   * @return The multiMeasEnabled.
   */
  public boolean isMultiMeasEnabled() {
    return multiMeasEnabled;
  }

  /**
   * Sets the multiMeasEnabled.
   * 
   * @param multiMeasEnabled the multiMeasEnabled to set.
   */
  public void setMultiMeasEnabled(boolean multiMeasEnabled) {
    this.multiMeasEnabled = multiMeasEnabled;
  }

  /**
   * Gets the nQubits.
   * 
   * @return The nQubits.
   */
  public int getnQubits() {
    return nQubits;
  }

  /**
   * Sets the nQubits.
   * 
   * @param nQubits the nQubits to set.
   */
  public void setnQubits(int nQubits) {
    this.nQubits = nQubits;
  }

  /**
   * Gets the nRegisters.
   * 
   * @return The nRegisters.
   */
  public int getnRegisters() {
    return nRegisters;
  }

  /**
   * Sets the nRegisters.
   * 
   * @param nRegisters the nRegisters to set.
   */
  public void setnRegisters(int nRegisters) {
    this.nRegisters = nRegisters;
  }

  /**
   * Gets the onlineDate.
   * 
   * @return The onlineDate.
   */
  public String getOnlineDate() {
    return onlineDate;
  }

  /**
   * Sets the onlineDate.
   * 
   * @param onlineDate the onlineDate to set.
   */
  public void setOnlineDate(String onlineDate) {
    this.onlineDate = onlineDate;
  }

  /**
   * Gets the openPulse.
   * 
   * @return The openPulse.
   */
  public boolean isOpenPulse() {
    return openPulse;
  }

  /**
   * Sets the openPulse.
   * 
   * @param openPulse the openPulse to set.
   */
  public void setOpenPulse(boolean openPulse) {
    this.openPulse = openPulse;
  }

  /**
   * Gets the parallelCompilation.
   * 
   * @return The parallelCompilation.
   */
  public boolean isParallelCompilation() {
    return parallelCompilation;
  }

  /**
   * Sets the parallelCompilation.
   * 
   * @param parallelCompilation the parallelCompilation to set.
   */
  public void setParallelCompilation(boolean parallelCompilation) {
    this.parallelCompilation = parallelCompilation;
  }

  /**
   * Gets the processorType.
   * 
   * @return The processorType.
   */
  public ProcessorType getProcessorType() {
    return processorType;
  }

  /**
   * Sets the processorType.
   * 
   * @param processorType the processorType to set.
   */
  public void setProcessorType(ProcessorType processorType) {
    this.processorType = processorType;
  }

  /**
   * Gets the quantumVolume.
   * 
   * @return The quantumVolume.
   */
  public Object getQuantumVolume() {
    return quantumVolume;
  }

  /**
   * Sets the quantumVolume.
   * 
   * @param quantumVolume the quantumVolume to set.
   */
  public void setQuantumVolume(Object quantumVolume) {
    this.quantumVolume = quantumVolume;
  }

  /**
   * Gets the repDelayRange.
   * 
   * @return The repDelayRange.
   */
  public List<Integer> getRepDelayRange() {
    return repDelayRange;
  }

  /**
   * Sets the repDelayRange.
   * 
   * @param repDelayRange the repDelayRange to set.
   */
  public void setRepDelayRange(List<Integer> repDelayRange) {
    this.repDelayRange = repDelayRange;
  }

  /**
   * Gets the sampleName.
   * 
   * @return The sampleName.
   */
  public String getSampleName() {
    return sampleName;
  }

  /**
   * Sets the sampleName.
   * 
   * @param sampleName the sampleName to set.
   */
  public void setSampleName(String sampleName) {
    this.sampleName = sampleName;
  }

  /**
   * Gets the simulator.
   * 
   * @return The simulator.
   */
  public boolean isSimulator() {
    return simulator;
  }

  /**
   * Sets the simulator.
   * 
   * @param simulator the simulator to set.
   */
  public void setSimulator(boolean simulator) {
    this.simulator = simulator;
  }

  /**
   * Gets the supportedFeatures.
   * 
   * @return The supportedFeatures.
   */
  public List<String> getSupportedFeatures() {
    return supportedFeatures;
  }

  /**
   * Sets the supportedFeatures.
   * 
   * @param supportedFeatures the supportedFeatures to set.
   */
  public void setSupportedFeatures(List<String> supportedFeatures) {
    this.supportedFeatures = supportedFeatures;
  }

  /**
   * Gets the supportedInstructions.
   * 
   * @return The supportedInstructions.
   */
  public List<String> getSupportedInstructions() {
    return supportedInstructions;
  }

  /**
   * Sets the supportedInstructions.
   * 
   * @param supportedInstructions the supportedInstructions to set.
   */
  public void setSupportedInstructions(List<String> supportedInstructions) {
    this.supportedInstructions = supportedInstructions;
  }

  /**
   * Gets the timingConstraints.
   * 
   * @return The timingConstraints.
   */
  public TimingConstraints getTimingConstraints() {
    return timingConstraints;
  }

  /**
   * Sets the timingConstraints.
   * 
   * @param timingConstraints the timingConstraints to set.
   */
  public void setTimingConstraints(TimingConstraints timingConstraints) {
    this.timingConstraints = timingConstraints;
  }

  /**
   * Gets the url.
   * 
   * @return The url.
   */
  public Object getUrl() {
    return url;
  }

  /**
   * Sets the url.
   * 
   * @param url the url to set.
   */
  public void setUrl(Object url) {
    this.url = url;
  }

  
}