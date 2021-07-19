package com.arkumbra.algo.graphs;

import java.util.HashSet;
import java.util.Set;

public class GraphVertex {
  Integer val;
  Set<GraphVertex> edges = new HashSet<>();

  public GraphVertex(int val) {
    this.val = val;
  }

  public GraphVertex(int val, Set<GraphVertex> edges) {
    this.val = val;
    this.edges = edges;
  }
}
