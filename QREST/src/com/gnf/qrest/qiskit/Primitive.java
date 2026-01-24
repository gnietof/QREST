package com.gnf.qrest.qiskit;

import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.PUB;
import java.util.List;

public abstract class Primitive<P extends PUB> {
  private Backend backend;

  public Primitive(Backend backend) {
    this.backend = backend;
  }

  public abstract Job run(P pub);

  public abstract Job run(List<P> pub);

  public Backend getBackend() {
    return backend;
  }

  public void setBackend(Backend backend) {
    this.backend = backend;
  }
}
