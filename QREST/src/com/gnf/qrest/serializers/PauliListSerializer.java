package com.gnf.qrest.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gnf.qrest.qiskit.Pauli;

public class PauliListSerializer extends JsonSerializer<List<Pauli>> {

    @Override
    public void serialize(
            List<Pauli> value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        for (Pauli p : value) {
//        	gen.writeNumberField(p.getLabel(), p.getCoeff().getReal());
        	gen.writeNumberField(p.getLabel(), p.getCoeff());
        }
        gen.writeEndObject();
    }
}

