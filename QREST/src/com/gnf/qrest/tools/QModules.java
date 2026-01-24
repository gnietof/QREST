package com.gnf.qrest.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class QModules {

  private HashMap<String, Set<String>> modules = new HashMap<String, Set<String>>();

  public void add(String path, String entry) {
    modules.computeIfAbsent(path, e -> new HashSet<String>()).add(entry);
  }

  public String dump() {
    StringBuffer sb = new StringBuffer();

    for (String key : modules.keySet()) {
      sb.append("from ");
      sb.append(key);
      sb.append(" import ");
      sb.append(String.join(",", modules.get(key)));
      sb.append(";\n");
    }

    sb.append("\n");
    return sb.toString();

  }

}
