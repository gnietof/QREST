package com.gnf.qrest.model.complex;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ComplexSerializer extends JsonSerializer<Complex> {

    @Override
    public void serialize(
            Complex value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

//        gen.writeStartArray();
//        gen.writeNumber(value.getReal());
//        gen.writeNumber(value.getImag());
//        gen.writeEndArray();
    	gen.writeNumber(value.getReal());
    }
}

