package com.gnf.qrest.model;

/**
 * Model a ProcessorType
 */
public class ProcessorType {
  private String family;
  private String revision;
  private String segment;

  /**
   * Gets the family.
   *
   * @return The family.
   */
  public String getFamily() {
    return family;
  }

  /**
   * Sets the family.
   * 
   * @param family The family to set.
   */
  public void setFamily(String family) {
    this.family = family;
  }

  /**
   * Gets the revision.
   *
   * @return The revision.
   */
  public String getRevision() {
    return revision;
  }

  /**
   * Sets the revision.
   * 
   * @param revision The revision to set.
   */
  public void setRevision(String revision) {
    this.revision = revision;
  }

  /**
   * Gets the segment.
   *
   * @return The segment.
   */
  public String getSegment() {
    return segment;
  }

  /**
   * Sets the segment.
   * 
   * @param segment The segment to set.
   */
  public void setSegment(String segment) {
    this.segment = segment;
  }

}
