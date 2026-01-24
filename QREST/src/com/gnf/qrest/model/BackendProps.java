package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gnf.qrest.model.BackendProps.Gate.GeneralQLists;
import com.gnf.qrest.model.BackendProps.Gate.Measure;
import java.util.List;

public class BackendProps {

  @JsonProperty("backend_name")
  private String backendName;

  @JsonProperty("backend_version")
  private String backendVersion;
  private List<Gate> gates;

  private List<Measure> general;

  @JsonProperty("general_qlists")
  private List<GeneralQLists> generalQLists;

  @JsonProperty("last_update_date")
  private String lastUpdateDate;

  private List<List<Measure>> qubits;

  public static class Gate {
    private String gate;
    private String id;
    private List<Parameter> parameters;

    private List<Integer> qubits;

    public static class Parameter {
      private String date;
      private String name;
      private String unit;
      private Double value;

      public String getDate() {
        return date;
      }

      public void setDate(String date) {
        this.date = date;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getUnit() {
        return unit;
      }

      public void setUnit(String unit) {
        this.unit = unit;
      }

      public Double getValue() {
        return value;
      }

      public void setValue(Double value) {
        this.value = value;
      }
    }

    public static class Measure {
      private String date;
      private String name;
      private String unit;
      private Double value;

      public String getDate() {
        return date;
      }

      public void setDate(String date) {
        this.date = date;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getUnit() {
        return unit;
      }

      public void setUnit(String unit) {
        this.unit = unit;
      }

      public Double getValue() {
        return value;
      }

      public void setValue(Double value) {
        this.value = value;
      }
    }

    public static class GeneralQLists {
      private String name;
      private List<Integer> qubits;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public List<Integer> getQubits() {
        return qubits;
      }

      public void setQubits(List<Integer> qubits) {
        this.qubits = qubits;
      }
    }

    public static class Qubit {

    }

    public String getGate() {
      return gate;
    }

    public void setGate(String gate) {
      this.gate = gate;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public List<Parameter> getParameters() {
      return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
      this.parameters = parameters;
    }

    public List<Integer> getQubits() {
      return qubits;
    }

    public void setQubits(List<Integer> qubits) {
      this.qubits = qubits;
    }

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

  public List<Gate> getGates() {
    return gates;
  }

  public void setGates(List<Gate> gates) {
    this.gates = gates;
  }

  public List<Measure> getGeneral() {
    return general;
  }

  public void setGeneral(List<Measure> general) {
    this.general = general;
  }

  public List<GeneralQLists> getGeneralQLists() {
    return generalQLists;
  }

  public void setGeneralQLists(List<GeneralQLists> generalQLists) {
    this.generalQLists = generalQLists;
  }

  public String getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(String lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public List<List<Measure>> getQubits() {
    return qubits;
  }

  public void setQubits(List<List<Measure>> qubits) {
    this.qubits = qubits;
  }

}
