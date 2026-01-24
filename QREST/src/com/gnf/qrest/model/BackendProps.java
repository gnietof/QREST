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

  /**
   * 
   */
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

      /**
       * Gets the date.
       * 
       * @return The date.
       */
      public String getDate() {
        return date;
      }

      /**
       * Sets the date.
       * 
       * @param date The date to set.
       */
      public void setDate(String date) {
        this.date = date;
      }

      /**
       * Gets the name.
       * 
       * @return The name.
       */
      public String getName() {
        return name;
      }

      /**
       * Sets the name.
       * 
       * @param name The name to set.
       */
      public void setName(String name) {
        this.name = name;
      }

      /**
       * Gets the unit.
       * 
       * @return The unit.
       */
      public String getUnit() {
        return unit;
      }

      /**
       * Sets the unit.
       * 
       * @param unit The unit to set.
       */
      public void setUnit(String unit) {
        this.unit = unit;
      }

      /**
       * Gets the value.
       * 
       * @return The value.
       */
      public Double getValue() {
        return value;
      }

      /**
       * Sets the value.
       * 
       * @param value The value to set.
       */
      public void setValue(Double value) {
        this.value = value;
      }

    }

    /**
     * Models a Measure.
     */
    public static class Measure {
      private String date;
      private String name;
      private String unit;
      private Double value;

      /**
       * Gets the date.
       * 
       * @return The date.
       */
      public String getDate() {
        return date;
      }

      /**
       * Sets the date.
       * 
       * @param date The date to set.
       */
      public void setDate(String date) {
        this.date = date;
      }

      /**
       * Gets the name.
       * 
       * @return The name.
       */
      public String getName() {
        return name;
      }

      /**
       * Sets the name.
       * 
       * @param name The name to set.
       */
      public void setName(String name) {
        this.name = name;
      }

      /**
       * Gets the unit.
       * 
       * @return The unit.
       */
      public String getUnit() {
        return unit;
      }

      /**
       * Sets the unit.
       * 
       * @param unit The unit to set.
       */
      public void setUnit(String unit) {
        this.unit = unit;
      }

      /**
       * Gets the value.
       * 
       * @return The value.
       */
      public Double getValue() {
        return value;
      }

      /**
       * Sets the value.
       * 
       * @param value The value to set.
       */
      public void setValue(Double value) {
        this.value = value;
      }

    }

    /**
     * Models a GeneralQLists.
     */
    public static class GeneralQLists {
      private String name;
      private List<Integer> qubits;

      /**
       * Gets the name.
       * 
       * @return The name.
       */
      public String getName() {
        return name;
      }

      /**
       * Sets the name.
       * 
       * @param name The name to set.
       */
      public void setName(String name) {
        this.name = name;
      }

      /**
       * Gets the qubits.
       * 
       * @return The qubits.
       */
      public List<Integer> getQubits() {
        return qubits;
      }

      /**
       * Sets the qubits.
       * 
       * @param qubits The qubits to set.
       */
      public void setQubits(List<Integer> qubits) {
        this.qubits = qubits;
      }


    }

    /**
     * Models a Qubit
     */
    public static class Qubit {

    }

    /**
     * Gets the gate.
     * 
     * @return The gate.
     */
    public String getGate() {
      return gate;
    }

    /**
     * Sets the gate.
     * 
     * @param gate The gate to set.
     */
    public void setGate(String gate) {
      this.gate = gate;
    }

    /**
     * Gets the id.
     * 
     * @return The id.
     */
    public String getId() {
      return id;
    }

    /**
     * Sets the id.
     * 
     * @param id The id to set.
     */
    public void setId(String id) {
      this.id = id;
    }

    /**
     * Gets the parameters.
     * 
     * @return The parameters.
     */
    public List<Parameter> getParameters() {
      return parameters;
    }

    /**
     * Sets the parameters.
     * 
     * @param parameters The parameters to set.
     */
    public void setParameters(List<Parameter> parameters) {
      this.parameters = parameters;
    }

    /**
     * Gets the qubits.
     * 
     * @return The qubits.
     */
    public List<Integer> getQubits() {
      return qubits;
    }

    /**
     * Sets the qubits.
     * 
     * @param qubits The qubits to set.
     */
    public void setQubits(List<Integer> qubits) {
      this.qubits = qubits;
    }



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
   * @param backendName The backendName to set.
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
   * @param backendVersion The backendVersion to set.
   */
  public void setBackendVersion(String backendVersion) {
    this.backendVersion = backendVersion;
  }

  /**
   * Gets the gates.
   * 
   * @return The gates.
   */
  public List<Gate> getGates() {
    return gates;
  }

  /**
   * Sets the gates.
   * 
   * @param gates The gates to set.
   */
  public void setGates(List<Gate> gates) {
    this.gates = gates;
  }

  /**
   * Gets the general.
   * 
   * @return The general.
   */
  public List<Measure> getGeneral() {
    return general;
  }

  /**
   * Sets the general.
   * 
   * @param general The general to set.
   */
  public void setGeneral(List<Measure> general) {
    this.general = general;
  }

  /**
   * Gets the generalQLists.
   * 
   * @return The generalQLists.
   */
  public List<GeneralQLists> getGeneralQLists() {
    return generalQLists;
  }

  /**
   * Sets the generalQLists.
   * 
   * @param generalQLists The generalQLists to set.
   */
  public void setGeneralQLists(List<GeneralQLists> generalQLists) {
    this.generalQLists = generalQLists;
  }

  /**
   * Gets the lastUpdateDate.
   * 
   * @return The lastUpdateDate.
   */
  public String getLastUpdateDate() {
    return lastUpdateDate;
  }

  /**
   * Sets the lastUpdateDate.
   * 
   * @param lastUpdateDate The lastUpdateDate to set.
   */
  public void setLastUpdateDate(String lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  /**
   * Gets the qubits.
   * 
   * @return The qubits.
   */
  public List<List<Measure>> getQubits() {
    return qubits;
  }

  /**
   * Sets the qubits.
   * 
   * @param qubits The qubits to set.
   */
  public void setQubits(List<List<Measure>> qubits) {
    this.qubits = qubits;
  }



}
