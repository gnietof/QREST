package com.gnf.qrest.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.model.EstimatorPUB;
import java.io.IOException;

/**
 * Serializer for EstimatorPUBSerializer.
 * 
 * @param qasm the qasm to set
 */
public class EstimatorPUBSerializer extends JsonSerializer<EstimatorPUB> {

  private PauliListSerializer pauliListSerializer = new PauliListSerializer();

  @Override
  public void serialize(EstimatorPUB value, JsonGenerator gen, SerializerProvider serializers) 
      throws IOException {

    gen.writeStartArray();
    gen.writeObject(value.getCircuit());
    pauliListSerializer.serialize(value.getObservables(), gen, serializers);
    gen.writeObject(value.getParameters());
    gen.writeObject(value.getPrecision());
    gen.writeEndArray();
  }

}
