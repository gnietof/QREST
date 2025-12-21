package com.gnf.qrest.wrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
import com.gnf.qrest.qiskit.Pauli;

public class PauliListWrapper {

    private List<Pauli> paulis;

    public PauliListWrapper(List<Pauli> paulis) { 
    	this.paulis = paulis; 
    }

    @JsonValue
    public Map<String, Double> toMap() {
        Map<String, Double> map = new HashMap<>();
        for (Pauli p : paulis) {
            // Use the float/double logic that worked for you
            map.put(p.getLabel(), p.getCoeff());
        }
        return map;
    }
}