package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Class which stores job results. 
 */
public class JobResults {

  private List<Result> results;
  private Metadata metadata;

  /**
   * Class which stores job result.
   */
  public static class Result {
    private Map<String, Data> data;
    private Metadata metadadata;

    /**
     * Class which stores job result data.
     */
    public static class Data {
      private C c;
      private List<Double> evs;
      private List<Double> stds;
      @JsonProperty("ensemble_standard_error")
      private List<Double> ensembleStandardError;

      /**
       * Class which stores C measures.
       */
      public static class C {
        private List<String> samples;
        
        @JsonProperty("num_bits")
        private int numBits;

        /**
         * Gets the samples.
         * 
         * @return The samples.
         */
        public List<String> getSamples() {
          return samples;
        }

        /**
         * Sets the samples.
         * 
         * @param samples The samples to set.
         */
        public void setSamples(List<String> samples) {
          this.samples = samples;
        }

        /**
         * Gets the numBits.
         * 
         * @return The numBits.
         */
        public int getNumBits() {
          return numBits;
        }

        /**
         * Sets the numBits.
         * 
         * @param numBits The numBits to set.
         */
        public void setNumBits(int numBits) {
          this.numBits = numBits;
        }


      }

      /**
       * Gets the c.
       * 
       * @return The c.
       */
      public C getC() {
        return c;
      }

      /**
       * Sets the c.
       * 
       * @param c The c to set.
       */
      public void setC(C c) {
        this.c = c;
      }

      /**
       * Gets the evs.
       * 
       * @return The evs.
       */
      public List<Double> getEvs() {
        return evs;
      }

      /**
       * Sets the evs.
       * 
       * @param evs The evs to set.
       */
      public void setEvs(List<Double> evs) {
        this.evs = evs;
      }

      /**
       * Gets the stds.
       * 
       * @return The stds.
       */
      public List<Double> getStds() {
        return stds;
      }

      /**
       * Sets the stds.
       * 
       * @param stds The stds to set.
       */
      public void setStds(List<Double> stds) {
        this.stds = stds;
      }

      /**
       * Gets the ensembleStandardError.
       * 
       * @return The ensembleStandardError.
       */
      public List<Double> getEnsembleStandardError() {
        return ensembleStandardError;
      }

      /**
       * Sets the ensembleStandardError.
       * 
       * @param ensembleStandardError The ensembleStandardError to set.
       */
      public void setEnsembleStandardError(List<Double> ensembleStandardError) {
        this.ensembleStandardError = ensembleStandardError;
      }

    }

    /**
     * Class which stores metadata information.
     */
    public static class Metadata {
      private int shots;

      @JsonProperty("target_precision")
      private double targetPrecision;
      @JsonProperty("num_ranzomizations")
      private int numRanzomizations;
      @JsonProperty("circuit_metadata")
      private CircuitMetadata circuitMetadata;
      private Resilience resilience;

      /**
       * Holds circuit metadata information.
       */
      public static class CircuitMetadata {
      }

      /**
       * Resilience configuration options.
       */
      public static class Resilience {
      }

      /**
       * Gets the shots.
       * 
       * @return The shots.
       */
      public int getShots() {
        return shots;
      }

      /**
       * Sets the shots.
       * 
       * @param shots The shots to set.
       */
      public void setShots(int shots) {
        this.shots = shots;
      }

      /**
       * Gets the targetPrecision.
       * 
       * @return The targetPrecision.
       */
      public double getTargetPrecision() {
        return targetPrecision;
      }

      /**
       * Sets the targetPrecision.
       * 
       * @param targetPrecision The targetPrecision to set.
       */
      public void setTargetPrecision(double targetPrecision) {
        this.targetPrecision = targetPrecision;
      }

      /**
       * Gets the numRanzomizations.
       * 
       * @return The numRanzomizations.
       */
      public int getNumRanzomizations() {
        return numRanzomizations;
      }

      /**
       * Sets the numRanzomizations.
       * 
       * @param numRanzomizations The numRanzomizations to set.
       */
      public void setNumRanzomizations(int numRanzomizations) {
        this.numRanzomizations = numRanzomizations;
      }

      /**
       * Gets the circuitMetadata.
       * 
       * @return The circuitMetadata.
       */
      public CircuitMetadata getCircuitMetadata() {
        return circuitMetadata;
      }

      /**
       * Sets the circuitMetadata.
       * 
       * @param circuitMetadata The circuitMetadata to set.
       */
      public void setCircuitMetadata(CircuitMetadata circuitMetadata) {
        this.circuitMetadata = circuitMetadata;
      }

      /**
       * Gets the resilience.
       * 
       * @return The resilience.
       */
      public Resilience getResilience() {
        return resilience;
      }

      /**
       * Sets the resilience.
       * 
       * @param resilience The resilience to set.
       */
      public void setResilience(Resilience resilience) {
        this.resilience = resilience;
      }

    }

    /**
     * Gets the data.
     * 
     * @return The data.
     */
    public Map<String, Data> getData() {
      return data;
    }

    /**
     * Sets the data.
     * 
     * @param data The data to set.
     */
    public void setData(Map<String, Data> data) {
      this.data = data;
    }

    /**
     * Gets the metadadata.
     * 
     * @return The metadadata.
     */
    public Metadata getMetadadata() {
      return metadadata;
    }

    /**
     * Sets the metadadata.
     * 
     * @param metadadata The metadadata to set.
     */
    public void setMetadadata(Metadata metadadata) {
      this.metadadata = metadadata;
    }


  }

  /**
   * Class which stores metadata information.
   */
  public static class Metadata {
    @JsonProperty("dynamical_decoupling")
    private DynamicalDecoupling dynamicalDecoupling;
    private Twirling twirling;
    private Resilience resilience;
    private int version = 2;

    /**
     * DynamicalDecoupling configuration options.
     */
    public static class DynamicalDecoupling {
      private boolean enable;
      @JsonProperty("sequence_type")
      private String sequenceType;
      @JsonProperty("extra_slack_distribution")
      private String extraSlackDistribution;
      @JsonProperty("scheduling_method")
      private String schedulingMethod;

      /**
       * Gets the enable.
       * 
       * @return The enable.
       */
      public boolean isEnable() {
        return enable;
      }

      /**
       * Sets the enable.
       * 
       * @param enable The enable to set.
       */
      public void setEnable(boolean enable) {
        this.enable = enable;
      }

      /**
       * Gets the sequenceType.
       * 
       * @return The sequenceType.
       */
      public String getSequenceType() {
        return sequenceType;
      }

      /**
       * Sets the sequenceType.
       * 
       * @param sequenceType The sequenceType to set.
       */
      public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
      }

      /**
       * Gets the extraSlackDistribution.
       * 
       * @return The extraSlackDistribution.
       */
      public String getExtraSlackDistribution() {
        return extraSlackDistribution;
      }

      /**
       * Sets the extraSlackDistribution.
       * 
       * @param extraSlackDistribution The extraSlackDistribution to set.
       */
      public void setExtraSlackDistribution(String extraSlackDistribution) {
        this.extraSlackDistribution = extraSlackDistribution;
      }

      /**
       * Gets the schedulingMethod.
       * 
       * @return The schedulingMethod.
       */
      public String getSchedulingMethod() {
        return schedulingMethod;
      }

      /**
       * Sets the schedulingMethod.
       * 
       * @param schedulingMethod The schedulingMethod to set.
       */
      public void setSchedulingMethod(String schedulingMethod) {
        this.schedulingMethod = schedulingMethod;
      }


    }

    /**
     * Twirling configuration options.
     */
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

      /**
       * Gets the enableGates.
       * 
       * @return The enableGates.
       */
      public boolean isEnableGates() {
        return enableGates;
      }

      /**
       * Sets the enableGates.
       * 
       * @param enableGates The enableGates to set.
       */
      public void setEnableGates(boolean enableGates) {
        this.enableGates = enableGates;
      }

      /**
       * Gets the enableMeasure.
       * 
       * @return The enableMeasure.
       */
      public boolean isEnableMeasure() {
        return enableMeasure;
      }

      /**
       * Sets the enableMeasure.
       * 
       * @param enableMeasure The enableMeasure to set.
       */
      public void setEnableMeasure(boolean enableMeasure) {
        this.enableMeasure = enableMeasure;
      }

      /**
       * Gets the numRandomizations.
       * 
       * @return The numRandomizations.
       */
      public String getNumRandomizations() {
        return numRandomizations;
      }

      /**
       * Sets the numRandomizations.
       * 
       * @param numRandomizations The numRandomizations to set.
       */
      public void setNumRandomizations(String numRandomizations) {
        this.numRandomizations = numRandomizations;
      }

      /**
       * Gets the shotsPerRandomizations.
       * 
       * @return The shotsPerRandomizations.
       */
      public String getShotsPerRandomizations() {
        return shotsPerRandomizations;
      }

      /**
       * Sets the shotsPerRandomizations.
       * 
       * @param shotsPerRandomizations The shotsPerRandomizations to set.
       */
      public void setShotsPerRandomizations(String shotsPerRandomizations) {
        this.shotsPerRandomizations = shotsPerRandomizations;
      }

      /**
       * Gets the interleaveRandomizations.
       * 
       * @return The interleaveRandomizations.
       */
      public boolean isInterleaveRandomizations() {
        return interleaveRandomizations;
      }

      /**
       * Sets the interleaveRandomizations.
       * 
       * @param interleaveRandomizations The interleaveRandomizations to set.
       */
      public void setInterleaveRandomizations(boolean interleaveRandomizations) {
        this.interleaveRandomizations = interleaveRandomizations;
      }

      /**
       * Gets the strategy.
       * 
       * @return The strategy.
       */
      public String getStrategy() {
        return strategy;
      }

      /**
       * Sets the strategy.
       * 
       * @param strategy The strategy to set.
       */
      public void setStrategy(String strategy) {
        this.strategy = strategy;
      }


    }

    /**
     * Resilience configuration options.
     */
    public static class Resilience {
      @JsonProperty("measure_mitigation")
      public boolean measureMitigation;
      
      @JsonProperty("zne_mitigation")
      public boolean zneMitigation;
      
      @JsonProperty("pec_mitigation")
      public boolean pecMitigation;

      /**
       * Gets the measureMitigation.
       * 
       * @return The measureMitigation.
       */
      public boolean isMeasureMitigation() {
        return measureMitigation;
      }

      /**
       * Sets the measureMitigation.
       * 
       * @param measureMitigation The measureMitigation to set.
       */
      public void setMeasureMitigation(boolean measureMitigation) {
        this.measureMitigation = measureMitigation;
      }

      /**
       * Gets the zneMitigation.
       * 
       * @return The zneMitigation.
       */
      public boolean isZneMitigation() {
        return zneMitigation;
      }

      /**
       * Sets the zneMitigation.
       * 
       * @param zneMitigation The zneMitigation to set.
       */
      public void setZneMitigation(boolean zneMitigation) {
        this.zneMitigation = zneMitigation;
      }

      /**
       * Gets the pecMitigation.
       * 
       * @return The pecMitigation.
       */
      public boolean isPecMitigation() {
        return pecMitigation;
      }

      /**
       * Sets the pecMitigation.
       * 
       * @param pecMitigation The pecMitigation to set.
       */
      public void setPecMitigation(boolean pecMitigation) {
        this.pecMitigation = pecMitigation;
      }
      

    }

    /**
     * Information about the execution spans.
     */
    public static class Execution {
      /**
       * Information about the spans.
       */
      public static class ExecutionSpans {
      }
    }

    /**
     * Gets the dynamicalDecoupling.
     *
     * @return The dynamicalDecoupling.
     */
    public DynamicalDecoupling getDynamicalDecoupling() {
      return dynamicalDecoupling;
    }

    /**
     * Sets the dynamicalDecoupling.
     *
     * @param dynamicalDecoupling The dynamicalDecoupling to set.
     */
    public void setDynamicalDecoupling(DynamicalDecoupling dynamicalDecoupling) {
      this.dynamicalDecoupling = dynamicalDecoupling;
    }

    /**
     * Gets the twirling.
     *
     * @return The twirling.
     */
    public Twirling getTwirling() {
      return twirling;
    }

    /**
     * Sets the twirling.
     *
     * @param twirling The twirling to set.
     */
    public void setTwirling(Twirling twirling) {
      this.twirling = twirling;
    }

    /**
     * Gets the resilience.
     *
     * @return The resilience.
     */
    public Resilience getResilience() {
      return resilience;
    }

    /**
     * Sets the resilience.
     *
     * @param resilience The resilience to set.
     */
    public void setResilience(Resilience resilience) {
      this.resilience = resilience;
    }

    /**
     * Gets the version.
     *
     * @return The version.
     */
    public int getVersion() {
      return version;
    }

    /**
     * Sets the version.
     *
     * @param version The version to set.
     */
    public void setVersion(int version) {
      this.version = version;
    }

  }

  /**
   * Gets the results.
   *
   * @return The results.
   */
  public List<Result> getResults() {
    return results;
  }

  /**
   * Sets the results.
   *
   * @param results The results to set.
   */
  public void setResults(List<Result> results) {
    this.results = results;
  }

  /**
   * Gets the metadata.
   *
   * @return The metadata.
   */
  public Metadata getMetadata() {
    return metadata;
  }

  /**
   * Sets the metadata.
   *
   * @param metadata The metadata to set.
   */
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }


}
