package com.gnf.qrest.tools;

public class QModule {

  private String path;
  private String entry;

  public QModule(String path, String entry) {
    this.path = path;
    this.entry = entry;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getEntry() {
    return entry;
  }

  public void setEntry(String entry) {
    this.entry = entry;
  }

}
