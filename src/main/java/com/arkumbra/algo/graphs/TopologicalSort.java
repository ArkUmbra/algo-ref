package com.arkumbra.algo.graphs;

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

}
