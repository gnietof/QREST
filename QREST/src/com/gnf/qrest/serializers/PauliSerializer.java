package com.gnf.qrest.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.qiskit.Pauli;

public class PauliSerializer extends JsonSerializer<Pauli> {

    @Override
    public void serialize(
            Pauli value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

//        gen.writeStartArray();
//        gen.writeString(value.getLabel());
//        gen.writeObject(value.getCoeff());
////        gen.writeNumber(value.getCoeff().getReal());
//        gen.writeEndArray();
////        gen.writeNumber(value.getCoeff());
    	gen.writeStartObject();
    	gen.writeNumberField(value.getLabel(),value.getCoeff());
    	gen.writeEndObject();
    }
}

