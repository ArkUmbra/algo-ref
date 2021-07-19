package com.arkumbra.algo.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

  public List<Integer> findAllConnectedValues(GraphVertex graphVertex) {
    List<Integer> foundValue = new ArrayList<>();

    List<GraphVertex> seen = new LinkedList<>();
    Queue<GraphVertex> exploreQueue = new ArrayDeque<>();

    exploreQueue.add(graphVertex);

    while (! exploreQueue.isEmpty()) {
      GraphVertex next = exploreQueue.remove();
      if (seen.contains(next)) continue;

      foundValue.add(next.val);

      for (GraphVertex connection : next.edges) {
        exploreQueue.add(connection);
      }

      seen.add(next);
    }

    return foundValue;

  }

}
