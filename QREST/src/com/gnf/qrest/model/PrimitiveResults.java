package com.gnf.qrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gnf.qrest.deserializers.BitStringFlatDeserializer;
import com.gnf.qrest.deserializers.DoubleFlatDeserializer;
import com.gnf.qrest.model.PrimitiveResults.Result.SamplerData.SamplerRegisters;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Models a PrimitiveResults. 
 */
public class PrimitiveResults extends QResponse {

  private List<Result> results;
  private Metadata metadata;

  /**
   * Models a Result. 
   */
  public static class Result {
    @JsonDeserialize(using = ResultDataDeserializer.class)
    private ResultData data;
    private Metadata metadadata;

    /**
     * Models a ResultData. 
     */
    public static interface ResultData {
    }

    /**
     * Serializer for ResultData. 
     */
    public static class ResultDataDeserializer
        extends JsonDeserializer<PrimitiveResults.Result.ResultData> {

      public ResultDataDeserializer() {
      }

      @Override
      public ResultData deserialize(JsonParser p, DeserializationContext ctxt) 
          throws IOException, JacksonException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);
        if (node.has("evs")) {
          return mapper.treeToValue(node, PrimitiveResults.Result.EstimatorData.class);
        }

        Map<String, SamplerRegisters> registers = mapper.convertValue(node,
            new TypeReference<Map<String, PrimitiveResults.Result.SamplerData.SamplerRegisters>>() {
            });

        SamplerData samplerData = new PrimitiveResults.Result.SamplerData();
        samplerData.setRegisters(registers);
        return samplerData;

      }

    }

    /**
     * Models a EstimatorData. 
     */
    public static class EstimatorData implements ResultData {
      @JsonDeserialize(using = DoubleFlatDeserializer.class)
      List<List<Double>> evs;
      @JsonDeserialize(using = DoubleFlatDeserializer.class)
      List<List<Double>> stds;

      @JsonDeserialize(using = DoubleFlatDeserializer.class)
      
      @JsonProperty("ensemble_standard_error")
      List<List<Double>> ensembleStandardError;

      public EstimatorData() {
      }

    }

    /**
     * Models a SamplerData. 
     */
    public static class SamplerData implements ResultData {

      private Map<String, SamplerRegisters> registers;

      public SamplerData() {
      }

      /**
       * Models a SamplerRegisters. 
       */
      public static class SamplerRegisters {
        @JsonDeserialize(using = BitStringFlatDeserializer.class)
        private List<BitString> samples;

        @JsonProperty("num_bits")
        private int numBits;

        public BitString getBitString() {
          return getBitString(-1);
        }

        public BitString getBitString(int index) {
          BitString bb = null;

          if (index < 0) {
            bb = new BitString(samples.stream().flatMap(b -> b.stream())
                .collect(Collectors.toList()));
          } else {
            bb = samples.get(index);
          }
          return bb;
        }

      }

      public Map<String, SamplerRegisters> getRegisters() {
        return registers;
      }

      public void setRegisters(Map<String, SamplerRegisters> registers) {
        this.registers = registers;
      }
    }

    /**
     * Models a Metadata. 
     */
    public static class Metadata {
      private int shots;

      @JsonProperty("target_precision")
      private double targetPrecision;

      @JsonProperty("num_randomizations")
      private int numRandomizations;

      @JsonProperty("circuit_metadata")
      private CircuitMetadata circuitMetadata;

      private Resilience resilience;
      private Twirling twirling;

      @JsonProperty("dynamical_decoupling")
      private DynamicalDecoupling dynamicalDecoupling;

      /**
       * Models a CircuitMetadata. 
       */
      public static class CircuitMetadata {
      }

      /**
       * Models a DynamicalDecoupling. 
       */
      public static class DynamicalDecoupling {
        private boolean enable;
        @JsonProperty("sequence_type")
        private String sequenceType;

        @JsonProperty("extra_slack_distribution")
        private String extraSlackDistribution;

        @JsonProperty("scheduling_method")
        private String schedulingMethod;

      }

      public static class Twirling {
        @JsonProperty("enable_gates")
        private boolean enableGates;

        @JsonProperty("enable_measure")
        private boolean enableMeasure;

        @JsonProperty("num_randomizations")
        private String numRandomizations;

        @JsonProperty("shots_per_randomization")
        private String shotsPerRandomization;

        @JsonProperty("interleave_randomizations")
        private boolean interleaveRandomizations;

        private boolean strategy;

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
         * Gets the shotsPerRandomization.
         * 
         * @return The shotsPerRandomization.
         */
        public String getShotsPerRandomization() {
          return shotsPerRandomization;
        }

        /**
         * Sets the shotsPerRandomization.
         * 
         * @param shotsPerRandomization The shotsPerRandomization to set.
         */
        public void setShotsPerRandomization(String shotsPerRandomization) {
          this.shotsPerRandomization = shotsPerRandomization;
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
        public boolean isStrategy() {
          return strategy;
        }

        /**
         * Sets the strategy.
         * 
         * @param strategy The strategy to set.
         */
        public void setStrategy(boolean strategy) {
          this.strategy = strategy;
        }


      }

      public static class Resilience {
        @JsonProperty("measure_mitigation")
        private boolean measureMitigation;

        @JsonProperty("zne_mitigation")
        private boolean zneMitigation;

        @JsonProperty("pec_mitigation")
        private boolean pecMitigation;

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
       * Gets the numRandomizations.
       * 
       * @return The numRandomizations.
       */
      public int getNumRandomizations() {
        return numRandomizations;
      }

      /**
       * Sets the numRandomizations.
       * 
       * @param numRandomizations The numRandomizations to set.
       */
      public void setNumRandomizations(int numRandomizations) {
        this.numRandomizations = numRandomizations;
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

    }

    /**
     * Gets the data.
     * 
     * @return The data.
     */
    public ResultData getData() {
      return data;
    }

    /**
     * Sets the data.
     * 
     * @param data The data to set.
     */
    public void setData(ResultData data) {
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

  public static class Metadata {
    @JsonProperty("dynamical_decoupling")
    private DynamicalDecoupling dynamicalDecoupling;
    private Twirling twirling;
    private Resilience resilience;
    private int version = 2;

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
       * @param enable the enable to set
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
       * @param sequenceType the sequenceType to set
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
       * @param extraSlackDistribution the extraSlackDistribution to set
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
       * @param schedulingMethod the schedulingMethod to set
       */
      public void setSchedulingMethod(String schedulingMethod) {
        this.schedulingMethod = schedulingMethod;
      }


    }

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

    public static class Execution {
      public static class ExecutionSpans {
      }
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
