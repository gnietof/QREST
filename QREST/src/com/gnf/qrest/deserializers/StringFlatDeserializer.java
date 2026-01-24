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

public class StringFlatDeserializer extends JsonDeserializer<List<List<String>>> {

  @Override
  public List<List<String>> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {

    List<List<String>> result = new ArrayList<List<String>>();

    JsonNode node = p.getCodec().readTree(p);
    if (node.isArray()) {
      JsonNode first = node.get(0);
      if (first.isTextual()) {
        List<String> inner = new ArrayList<String>();
        for (JsonNode n : node) {
          inner.add(n.asText());
        }
        result.add(inner);
      } else if (first.isArray()) {
        for (JsonNode n : node) {
          List<String> inner = new ArrayList<String>();
          for (JsonNode n2 : n) {
            inner.add(n2.asText());
          }
          result.add(inner);
        }
      } else {
        throw JsonMappingException.from(p, "Unexpected SamplerPUB node: " + first);
      }
    }
    return result;
  }

}
