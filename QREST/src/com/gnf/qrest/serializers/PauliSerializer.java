package com.gnf.qrest.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.qiskit.Pauli;
import java.io.IOException;

public class PauliSerializer extends JsonSerializer<Pauli> {

  @Override
  public void serialize(Pauli value, JsonGenerator gen, SerializerProvider serializers) 
      throws IOException {

    gen.writeStartObject();
    gen.writeNumberField(value.getLabel(), value.getCoeff());
    gen.writeEndObject();
  }
}
