package com.arkumbra.algo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

  public List<Integer> findAllConnectedValues(GraphVertex graphVertex) {
    List<Integer> foundValue = new ArrayList<>();

    List<GraphVertex> seen = new LinkedList<>();
    Stack<GraphVertex> exploreOrder = new Stack<>();
    exploreOrder.push(graphVertex);

    while (! exploreOrder.isEmpty()) {
      GraphVertex next = exploreOrder.pop();
      if (seen.contains(next)) continue;

      foundValue.add(next.val);

      for (GraphVertex connection : next.edges) {
        exploreOrder.push(connection);
      }

      seen.add(next);
    }

    return foundValue;
  }

  public List<Integer> findAllConnectedValuesRecursive(Graph graph) {
    List<Integer> foundValue = new ArrayList<>();
    List<GraphVertex> seen = new LinkedList<>();

    for (GraphVertex vertex : graph.vertexs) {
      recurse(vertex, seen, foundValue);
    }

    return foundValue;
  }

  public List<Integer> findAllConnectedValuesRecursive(GraphVertex graphVertex) {
    List<Integer> foundValue = new ArrayList<>();
    List<GraphVertex> seen = new LinkedList<>();

    recurse(graphVertex, seen, foundValue);

    return foundValue;
  }

  private void recurse(GraphVertex graphVertex, List<GraphVertex> seen, List<Integer> foundValues) {
    if (seen.contains(graphVertex)) return;

    foundValues.add(graphVertex.val);
    seen.add(graphVertex);

    for (GraphVertex connection : graphVertex.edges) {
      recurse(connection, seen, foundValues);
    }
  }

}
