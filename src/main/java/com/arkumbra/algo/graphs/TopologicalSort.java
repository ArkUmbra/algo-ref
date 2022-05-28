package com.arkumbra.algo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSort<T> {

  public List<T> sort(List<Vertex<T>> vertices) {
    LinkedList<T> topSorted = new LinkedList<>();

    int time = 0;
    for (int i = 0; i < vertices.size(); i++) {
      Vertex<T> vert = vertices.get(i);
      if (vert.color == Vertex.WHITE) {
        dfsVisit(vertices, i, time, topSorted);
      }
    }

    return topSorted;
  }

  private void dfsVisit(List<Vertex<T>> vertices, int currentPos, int time,
      LinkedList<T> topSorted) {

    time++;
    Vertex<T> current = vertices.get(currentPos);
    current.start = time;
    current.color = Vertex.GREY;
    for (Integer edge : current.edges) {
      Vertex<T> con = vertices.get(edge);
      if (con.color == Vertex.WHITE) {
        con.predecessor = currentPos;
        dfsVisit(vertices, edge, time, topSorted);
      }
    }
    current.color = Vertex.BLACK;
    topSorted.addFirst(current.value);
    time++;
    current.finish = time;
  }


  static class Vertex<T> {
    private static final int WHITE = 0;
    private static final int GREY = 1;
    private static final int BLACK = 2;
    T value;
    int start;
    int finish;
    int color = WHITE;
    List<Integer> edges;
    int predecessor = -1;

    public Vertex(T t, List<Integer> edges) {
      this.value = t;
      this.edges = edges;
    }
  }


  public List<Integer> sortSimplified(int totalNodes, List<int[]> edges) {
    LinkedList<Integer> sorted = new LinkedList<>();

    List<List<Integer>> graph = buildGraph(totalNodes, edges);

    boolean[] seen = new boolean[totalNodes];
    for (int i = 0; i < totalNodes; i++) {
      if (! seen[i])
        dfsVisit(graph, i, sorted, seen);
    }

    return sorted;
  }

  private List<List<Integer>> buildGraph(int totalNodes, List<int[]> edges) {
    List<List<Integer>> graph = new ArrayList<>(totalNodes);

    for (int i = 0; i < totalNodes; i++)
      graph.add(new ArrayList<>());
    for (int[] edge : edges)
      graph.get(edge[0]).add(edge[1]);

    return graph;
  }

  private void dfsVisit(List<List<Integer>> graph, int i, LinkedList<Integer> sorted, boolean[] seen) {
    seen[i] = true;
    for (Integer connection : graph.get(i)) {
      if (! seen[connection])
        dfsVisit(graph, connection, sorted, seen);
    }
    sorted.addFirst(i);
  }

}
