package com.gnf.qrest.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.model2.Complex;

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

