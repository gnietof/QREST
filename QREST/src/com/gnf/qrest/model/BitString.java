package com.gnf.qrest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class BitString implements Iterable<String> {

    private final List<String> strings;
    
    public BitString() {
    	strings = new ArrayList<String>();
    }

    @JsonCreator
    public BitString(List<String> strings) {
        this.strings = List.copyOf(strings); // defensive copy
    }

    @JsonValue
    public List<String> asList() {
        return strings;
    }

    public int size() {
        return strings.size();
    }

    public void add(String string) {
        strings.add(string);
    }

    public void addAll(Collection<String> strings) {
        this.strings.addAll(strings);
    }

    public String get(int index) {
        return strings.get(index);
    }

    @Override
	public Iterator<String> iterator() {
        return strings.iterator();
    }

    public Stream<String> stream() {
        return strings.stream();
    }
    
    @Override
    public String toString() {
    	return String.join(", ", strings);
    }
}
