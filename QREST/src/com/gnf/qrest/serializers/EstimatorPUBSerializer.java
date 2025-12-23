package com.gnf.qrest.serializers;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.builders.EstimatorPUB;

public class EstimatorPUBSerializer extends JsonSerializer<EstimatorPUB> {
	
	private PauliListSerializer pauliListSerializer = new PauliListSerializer();

    @Override
    public void serialize(
    		EstimatorPUB value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        gen.writeStartArray();
        gen.writeObject(value.circuit());
    	pauliListSerializer.serialize(value.observables(), gen, serializers);
        gen.writeObject(value.parameters());
        gen.writeObject(value.precision());
        gen.writeEndArray();
    }
	
	
}
