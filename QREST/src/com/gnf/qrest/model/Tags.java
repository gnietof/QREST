package com.gnf.qrest.model;

import java.util.List;

/**
 * Class for modeling job tags 
 */
public class Tags {

  private List<String> tags;

  public Tags() {
  }

  public Tags(List<String> tags) {
    this.tags = tags;
  }

  public Tags(String... tags) {
    this.tags = List.of(tags);
  }

  /**
   * Gets the tags.
   *
   * @return The tags.
   */
  public List<String> getTags() {
    return tags;
  }

  /**
   * Sets the tags.
   *
   * @param tags The tags to set.
   */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }


}
