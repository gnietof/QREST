package com.gnf.qrest.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.model.Paulis;
import com.gnf.qrest.qiskit.Pauli;
import java.io.IOException;
import java.util.List;

/**
 * Serializer for PauliList.
 */
public class PauliListSerializer extends JsonSerializer<List<Paulis>> {

  @Override
  public void serialize(List<Paulis> value, JsonGenerator gen, SerializerProvider serializers) 
      throws IOException {

    gen.writeStartArray();
    for (Paulis pauli : value) {
      gen.writeStartObject();
      for (Pauli p : pauli.asList()) {
        gen.writeNumberField(p.getLabel(), p.getCoeff());
      }
      gen.writeEndObject();
    }
    gen.writeEndArray();
  }
}
