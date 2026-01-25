package com.gnf.qrest.qiskit;

import com.gnf.qrest.model.Backend;
import com.gnf.qrest.model.PUB;
import java.util.List;


/**
 * Abstract class for Primitives.
 *
 * @param <P> Class implementing the type of primitive.
 */
public abstract class Primitive<P extends PUB> {
  private Backend backend;


  /**
   * Constructor for a backend.
   *
   * @param backend The backend.
   */
  public Primitive(Backend backend) {
    this.backend = backend;
  }

  /**
   * Abstract method for running a primitive.
   *
   * @param pub The PUB used.
   * @return The job run.
   */
  public abstract Job run(P pub);

  /**
   * Abstract method for running a primitive.
   *
   * @param pub The list of PUBs used.
   * @return The job run.
   */
  public abstract Job run(List<P> pub);

  /**
   * Gets the backend.
   *
   *  @return the backend
   */
  public Backend getBackend() {
    return backend;
  }

  /**
   * Sets the backend.
   *
   * @param backend the backend to set
   */
  public void setBackend(Backend backend) {
    this.backend = backend;
  }

}
