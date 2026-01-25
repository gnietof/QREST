package com.gnf.qrest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gnf.qrest.serializers.SamplerPUBSerializer;

/**
 * Models a SamplerPUB.
 */
@JsonSerialize(using = SamplerPUBSerializer.class)
public class SamplerPUB extends PUB {

  private int shots;

  /**
   * Constructor for SamplerPUB from builder.
   */
  public SamplerPUB(Builder builder) {
    super(builder);
    this.shots = builder.shots;
  }

  /**
   * Builder for SamplerPUB.
   */
  public static class Builder extends PUB.Builder<Builder> {
    private int shots = 16;

    /**
     * Build using number of shots.
     */
    public Builder shots(int shots) {
      this.shots = shots;
      return this;
    }

    @Override
    public SamplerPUB build() {
      return new SamplerPUB(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  /**
   * Gets the shots.
   *
   * @return The shots.
   */
  public int getShots() {
    return shots;
  }

  /**
   * Sets the shots.
   *
   * @param shots the shots to set
   */
  public void setShots(int shots) {
    this.shots = shots;
  }

}
