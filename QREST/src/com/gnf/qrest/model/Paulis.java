package com.gnf.qrest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.gnf.qrest.qiskit.Pauli;

public final class Paulis implements Iterable<Pauli> {

    private final List<Pauli> paulis;
    
    public Paulis() {
    	paulis = new ArrayList<Pauli>();
    }

    @JsonCreator
    public Paulis(List<Pauli> paulis) {
        this.paulis = List.copyOf(paulis); // defensive copy
    }

    public Paulis(Pauli... paulis) {
        this.paulis = List.of(paulis);
    }
    
    @JsonValue
    public List<Pauli> asList() {
        return paulis;
    }

    public int size() {
        return paulis.size();
    }

    public void add(Pauli pauli) {
        paulis.add(pauli);
    }

    public void addAll(Collection<? extends Pauli> paulis) {
        this.paulis.addAll(paulis);
    }

    public Pauli get(int index) {
        return paulis.get(index);
    }

    @Override
	public Iterator<Pauli> iterator() {
        return paulis.iterator();
    }
    
    public Stream<Pauli> stream() {
        return paulis.stream();
    }    
}
