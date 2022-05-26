package com.arkumbra.algo.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KosarajuSCC {

  public List<List<Integer>> findStronglyConnectedComponents(int numberNodes, int[][] edges) {
    List<List<Integer>> ans = new ArrayList<>();

    List<List<Integer>> graph = new ArrayList<>();
    List<List<Integer>> reverseGraph = new ArrayList<>();
    buildGraph(numberNodes, edges, graph, reverseGraph);

    boolean[] visited = new boolean[numberNodes];

    LinkedList<Integer> order = new LinkedList<>();
    for (int i = 0; i < numberNodes; i++)
      visit(graph, i, visited, order);

    visited = new boolean[numberNodes]; // re-use data
    while (! order.isEmpty()) {
      Integer vertex = order.removeLast();
      List<Integer> scc = new ArrayList<>();
      assign(reverseGraph, vertex, visited, scc);
      if (! scc.isEmpty())
        ans.add(scc);
    }
    return ans;
  }

  private void visit(List<List<Integer>> graph, int i, boolean[] visited, List<Integer> order) {
    if (! visited[i]) {
      visited[i] = true;
      for (int neighbour : graph.get(i))
        visit(graph, neighbour, visited, order);
      order.add(i); // post-order
    }
  }

  private void assign(List<List<Integer>> graph, int i, boolean[] assigned, List<Integer> scc) {
    if (!assigned[i]) {
      assigned[i] = true;
      scc.add(i);
      for (Integer neighbour : graph.get(i))
        assign(graph, neighbour, assigned, scc);
    }
  }

  private void buildGraph(int numberNodes, int[][] edges,
      List<List<Integer>> graph, List<List<Integer>> reverseGraph) {

    for (int i = 0; i < numberNodes; i++) {
      graph.add(new ArrayList<>());
      reverseGraph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      reverseGraph.get(edge[1]).add(edge[0]);
    }
  }
}
