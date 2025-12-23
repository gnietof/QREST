package com.gnf.qrest.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;
import com.gnf.qrest.qiskit.Pauli;

public class PauliListWrapper {

    private List<List<Pauli>> paulis;

//    public PauliListWrapper(List<Pauli> paulis) { 
    public PauliListWrapper(List<List<Pauli>> paulis) { 
    	this.paulis = paulis; 
    }

    @JsonValue
    public List<Map<String, Double>> toMap() {
        List<Map<String, Double>> list = new ArrayList<Map<String,Double>>();
        for (List<Pauli> pauli: paulis) {
        	HashMap<String, Double> map = new HashMap<String,Double>();
	        for (Pauli p : pauli) {
	            map.put(p.getLabel(), p.getCoeff());
	        }
	        list.add(map);
        }
        return list;
    }
}