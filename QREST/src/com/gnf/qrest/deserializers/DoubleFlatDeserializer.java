package com.gnf.qrest.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer for Double arrays.
 *
 * @param qasm the qasm to set
 */
public class DoubleFlatDeserializer extends JsonDeserializer<List<List<Double>>> {

  @Override
  public List<List<Double>> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {

    List<List<Double>> result = new ArrayList<List<Double>>();

    JsonNode node = p.getCodec().readTree(p);
    if (node.isArray()) {
      JsonNode first = node.get(0);
      if (first.isNumber()) {
        List<Double> inner = new ArrayList<Double>();
        for (JsonNode n : node) {
          inner.add(n.asDouble());
        }
        result.add(inner);
      } else if (first.isArray()) {
        for (JsonNode n : node) {
          List<Double> inner = new ArrayList<Double>();
          for (JsonNode n2 : n) {
            inner.add(n2.asDouble());
          }
          result.add(inner);
        }
      } else {
        throw JsonMappingException.from(p, "Unexpected EstimatorPUB node: " + first);
      }
    }
    return result;
  }

}
