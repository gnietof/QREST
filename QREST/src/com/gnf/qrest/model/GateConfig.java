package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GateConfig {

  @JsonProperty("coupling_map")
  private List<List<Integer>> couplingMap;

  private String name;
  private List<String> parameters;

  @JsonProperty("qasm_def")
  private String qasmDef;

  public List<List<Integer>> getCouplingMap() {
    return couplingMap;
  }

  public void setCouplingMap(List<List<Integer>> couplingMap) {
    this.couplingMap = couplingMap;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getParameters() {
    return parameters;
  }

  public void setParameters(List<String> parameters) {
    this.parameters = parameters;
  }

  public String getQasmDef() {
    return qasmDef;
  }

  public void setQasmDef(String qasmDef) {
    this.qasmDef = qasmDef;
  }

}