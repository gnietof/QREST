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
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrimitiveResults extends QResponse {

  private List<Result> results;
  private Metadata metadata;

  public static class Result {
    @JsonDeserialize(using = ResultDataDeserializer.class)
    private ResultData data;
    private Metadata metadadata;

    public static interface ResultData {
    }

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

      public List<List<Double>> getEvs() {
        return evs;
      }

      public void setEvs(List<List<Double>> evs) {
        this.evs = evs;
      }

      public List<List<Double>> getStds() {
        return stds;
      }

      public void setStds(List<List<Double>> stds) {
        this.stds = stds;
      }

      public List<List<Double>> getEnsembleStandardError() {
        return ensembleStandardError;
      }

      public void setEnsembleStandardError(List<List<Double>> ensembleStandardError) {
        this.ensembleStandardError = ensembleStandardError;
      }

    }

    public static class SamplerData implements ResultData {

      private Map<String, SamplerRegisters> registers;

      public SamplerData() {
      }

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
            bb = new BitString(
                samples.stream().flatMap(b -> b.stream()).collect(Collectors.toList()));
          } else {
            bb = samples.get(index);
          }
          return bb;
        }

        public List<BitString> getSamples() {
          return samples;
        }

        public void setSamples(List<BitString> samples) {
          this.samples = samples;
        }

        public int getNumBits() {
          return numBits;
        }

        public void setNumBits(int numBits) {
          this.numBits = numBits;
        }

        public int size() {
          return samples.size();
        }

        public Map<String, Long> getCounts() {
          return getCounts(-1);
        }

        public Map<String, Long> getCounts(int index) {
          BitString bb = getBitString(index);
          Map<String, Long> counts = bb.stream()
              .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
          return counts;
        }

        public Map<Integer, Long> getIntCounts() {
          return getIntCounts(-1);
        }

        public Map<Integer, Long> getIntCounts(int index) {
          BitString bb = getBitString(index);
          Map<Integer, Long> counts = bb.stream()
              .collect(Collectors.groupingBy(b -> Integer.decode(b), Collectors.counting()));
          return counts;
        }
      }

      public Map<String, SamplerRegisters> getRegisters() {
        return registers;
      }

      public void setRegisters(Map<String, SamplerRegisters> registers) {
        this.registers = registers;
      }
    }

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

      public static class CircuitMetadata {

      }

      public static class DynamicalDecoupling {
        private boolean enable;
        @JsonProperty("sequence_type")
        private String sequenceType;

        @JsonProperty("extra_slack_distribution")
        private String extraSlackDistribution;

        @JsonProperty("scheduling_method")
        private String schedulingMethod;

        public boolean isEnable() {
          return enable;
        }

        public void setEnable(boolean enable) {
          this.enable = enable;
        }

        public String getSequenceType() {
          return sequenceType;
        }

        public void setSequenceType(String sequenceType) {
          this.sequenceType = sequenceType;
        }

        public String getExtraSlackDistribution() {
          return extraSlackDistribution;
        }

        public void setExtraSlackDistribution(String extraSlackDistribution) {
          this.extraSlackDistribution = extraSlackDistribution;
        }

        public String getSchedulingMethod() {
          return schedulingMethod;
        }

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

        @JsonProperty("shots_per_randomization")
        private String shotsPerRandomization;

        @JsonProperty("interleave_randomizations")
        private boolean interleaveRandomizations;

        private boolean strategy;

        public boolean isEnableGates() {
          return enableGates;
        }

        public void setEnableGates(boolean enableGates) {
          this.enableGates = enableGates;
        }

        public boolean isEnableMeasure() {
          return enableMeasure;
        }

        public void setEnableMeasure(boolean enableMeasure) {
          this.enableMeasure = enableMeasure;
        }

        public String getNumRandomizations() {
          return numRandomizations;
        }

        public void setNumRandomizations(String numRandomizations) {
          this.numRandomizations = numRandomizations;
        }

        public String getShotsPerRandomization() {
          return shotsPerRandomization;
        }

        public void setShotsPerRandomization(String shotsPerRandomization) {
          this.shotsPerRandomization = shotsPerRandomization;
        }

        public boolean isInterleaveRandomizations() {
          return interleaveRandomizations;
        }

        public void setInterleaveRandomizations(boolean interleaveRandomizations) {
          this.interleaveRandomizations = interleaveRandomizations;
        }

        public boolean isStrategy() {
          return strategy;
        }

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

        public boolean isMeasureMitigation() {
          return measureMitigation;
        }

        public void setMeasureMitigation(boolean measureMitigation) {
          this.measureMitigation = measureMitigation;
        }

        public boolean isZneMitigation() {
          return zneMitigation;
        }

        public void setZneMitigation(boolean zneMitigation) {
          this.zneMitigation = zneMitigation;
        }

        public boolean isPecMitigation() {
          return pecMitigation;
        }

        public void setPecMitigation(boolean pecMitigation) {
          this.pecMitigation = pecMitigation;
        }

      }

      public int getShots() {
        return shots;
      }

      public void setShots(int shots) {
        this.shots = shots;
      }

      public Resilience getResilience() {
        return resilience;
      }

      public void setResilience(Resilience resilience) {
        this.resilience = resilience;
      }

      public double getTargetPrecision() {
        return targetPrecision;
      }

      public void setTargetPrecision(double targetPrecision) {
        this.targetPrecision = targetPrecision;
      }

      public CircuitMetadata getCircuitMetadata() {
        return circuitMetadata;
      }

      public void setCircuitMetadata(CircuitMetadata circuitMetadata) {
        this.circuitMetadata = circuitMetadata;
      }

      public int getNumRandomizations() {
        return numRandomizations;
      }

      public void setNumRandomizations(int numRandomizations) {
        this.numRandomizations = numRandomizations;
      }

      public Twirling getTwirling() {
        return twirling;
      }

      public void setTwirling(Twirling twirling) {
        this.twirling = twirling;
      }

      public DynamicalDecoupling getDynamicalDecoupling() {
        return dynamicalDecoupling;
      }

      public void setDynamicalDecoupling(DynamicalDecoupling dynamicalDecoupling) {
        this.dynamicalDecoupling = dynamicalDecoupling;
      }
    }

    public Metadata getMetadadata() {
      return metadadata;
    }

    public void setMetadadata(Metadata metadadata) {
      this.metadadata = metadadata;
    }

    public ResultData getData() {
      return data;
    }

    public void setData(ResultData data) {
      this.data = data;
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

      public boolean isEnable() {
        return enable;
      }

      public void setEnable(boolean enable) {
        this.enable = enable;
      }

      public String getSequenceType() {
        return sequenceType;
      }

      public void setSequenceType(String sequenceType) {
        this.sequenceType = sequenceType;
      }

      public String getExtraSlackDistribution() {
        return extraSlackDistribution;
      }

      public void setExtraSlackDistribution(String extraSlackDistribution) {
        this.extraSlackDistribution = extraSlackDistribution;
      }

      public String getSchedulingMethod() {
        return schedulingMethod;
      }

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

      public String getStrategy() {
        return strategy;
      }

      public void setStrategy(String strategy) {
        this.strategy = strategy;
      }

      public boolean isEnableGates() {
        return enableGates;
      }

      public void setEnableGates(boolean enableGates) {
        this.enableGates = enableGates;
      }

      public boolean isEnableMeasure() {
        return enableMeasure;
      }

      public void setEnableMeasure(boolean enableMeasure) {
        this.enableMeasure = enableMeasure;
      }

      public String getNumRandomizations() {
        return numRandomizations;
      }

      public void setNumRandomizations(String numRandomizations) {
        this.numRandomizations = numRandomizations;
      }

      public String getShotsPerRandomizations() {
        return shotsPerRandomizations;
      }

      public void setShotsPerRandomizations(String shotsPerRandomizations) {
        this.shotsPerRandomizations = shotsPerRandomizations;
      }

      public boolean isInterleaveRandomizations() {
        return interleaveRandomizations;
      }

      public void setInterleaveRandomizations(boolean interleaveRandomizations) {
        this.interleaveRandomizations = interleaveRandomizations;
      }

    }

    public static class Resilience {
      @JsonProperty("measure_mitigation")
      public boolean measureMitigation;
      @JsonProperty("zne_mitigation")
      public boolean ZNEMitigation;
      @JsonProperty("pec_mitigation")
      public boolean PECMitigation;

      public boolean isMeasureMitigation() {
        return measureMitigation;
      }

      public void setMeasureMitigation(boolean measureMitigation) {
        this.measureMitigation = measureMitigation;
      }

      public boolean isZNEMitigation() {
        return ZNEMitigation;
      }

      public void setZNEMitigation(boolean zNEMitigation) {
        ZNEMitigation = zNEMitigation;
      }

      public boolean isPECMitigation() {
        return PECMitigation;
      }

      public void setPECMitigation(boolean pECMitigation) {
        PECMitigation = pECMitigation;
      }

    }

    public static class Execution {
      public static class ExecutionSpans {
      }
    }

    public DynamicalDecoupling getDynamical_decoupling() {
      return dynamicalDecoupling;
    }

    public void setDynamical_decoupling(DynamicalDecoupling dynamical_decoupling) {
      this.dynamicalDecoupling = dynamical_decoupling;
    }

    public Twirling getTwirling() {
      return twirling;
    }

    public void setTwirling(Twirling twirling) {
      this.twirling = twirling;
    }

    public Resilience getResilience() {
      return resilience;
    }

    public void setResilience(Resilience resilience) {
      this.resilience = resilience;
    }

    public int getVersion() {
      return version;
    }

    public void setVersion(int version) {
      this.version = version;
    }

  }

  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

  public Metadata getMetadata() {
    return metadata;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

}
