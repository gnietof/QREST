package com.gnf.qrest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gnf.qrest.qiskit.SparsePauliOp;
import com.gnf.qrest.serializers.EstimatorPUBSerializer;
import java.util.List;

@JsonSerialize(using = EstimatorPUBSerializer.class)
public class EstimatorPUB extends PUB {
  private List<Paulis> observables;
  private double precision;

  public EstimatorPUB(Builder builder) {
    super(builder);
    this.observables = builder.observables;
    this.precision = builder.precision;
  }

  public static class Builder extends PUB.Builder<Builder> {
    private List<Paulis> observables;
    private double precision = 0.015625;

    public Builder precision(double precision) {
      this.precision = precision;
      return this;
    }

    public Builder observables(List<Paulis> observables) {
      this.observables = observables;
      return this;
    }

    public Builder observable(Paulis observable) {
      this.observables = List.of(observable);
      return this;
    }

    public Builder observable(SparsePauliOp observable) {
      this.observables = List.of(observable.getPaulis());
      return this;
    }

    @Override
    public EstimatorPUB build() {
      return new EstimatorPUB(this);
    }

    @Override
    protected Builder self() {
      return this;
    }

  }

  public List<Paulis> getObservables() {
    return observables;
  }

  public void setObservables(List<Paulis> observables) {
    this.observables = observables;
  }

  public double getPrecision() {
    return precision;
  }

  public void setPrecision(double precision) {
    this.precision = precision;
  }

}
