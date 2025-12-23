package com.gnf.qrest.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.model.SamplerPUB;

public class SamplerPUBSerializer extends JsonSerializer<SamplerPUB> {

    @Override
    public void serialize(
    		SamplerPUB value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        gen.writeStartArray();
        gen.writeObject(value.getCircuit());
        gen.writeObject(value.getParameters());
        gen.writeObject(value.getShots());
        gen.writeEndArray();
    }
	
	
}
