package com.arkumbra.algo.graphs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StronglyConnectedComponentFinder {






  // Use Kosaraju's Two-Pass Algorithm
  public int[] sizesOfXLargestSCC(Graph graph, int x) {
    int[] results = new int[x];

    // Reverse

    return results;
  }


  private Integer sCurrentSourceVertex;
  private List<Integer> order = new ArrayList<>();
  private Integer[] leaders = new Integer[0];


  // Use Kosaraju's Two-Pass Algorithm
  public int[] sizesOfXLargestSCC(AdjacencyGraph graph, int x) {
    int[] results = new int[x];

    // Reverse
    AdjacencyGraph transpose = graph.buildTranspose();

    // DFS Loop one - compute ordering based on finishing time
//    computeFinishingTimeOrdering(transpose, 0, 0);
    // DFS Loop two - based on ordering discovered by loop one

    return results;
  }


//  private void firstDfs()


  private void dfsLoop(AdjacencyGraph graph) {
//    dfsLoop(graph, null);
  }

  private void dfsLoop(AdjacencyGraph graph, List<Integer> order, Integer s, List<Integer> seen) {
    //if (order != null) TODO

    for (int i = order.size() - 1; i >= 0; i--) {
//      No
//      Node node = graph.getAdj().get(i);
//      if (! seen.contains(i)) {
//
//      }
    }
  }


  private void dfs(AdjacencyGraph graph, Integer s, Integer i, Set<Integer> seen) {
    seen.add(i);
    leaders[i] = s;

    for (int j = 0; j < graph.getAdj().get(i).size(); j++) {
//      Integer vertex = graph.getAdj().get(i).get(j);
//      if (! seen.contains(vertex)) {
//        dfs(graph);
//      }
    }

  }

  private List<Integer> computeFinishingTimeOrdering(AdjacencyGraph adjacencyGraph, int nodesProcessedSoFar) {

    List<Integer> seen = new ArrayList<>();
    for (int i = 0; i < adjacencyGraph.getAdj().size(); i++) {
//      List<Integer> vertex = adjacencyGraph.getAdj().get(i);

//      if (seen.contains(vertex)) continue;

      computeFinishingTimeOrdering(adjacencyGraph, i++);
    }

    return null;

  }

  private void computeSccs(AdjacencyGraph adjacencyGraph, int sourceVertexIndex) {

//    for (List<Integer> integers : adjacencyGraph.getAdj()) {
//
//    }

  }













  public Graph reverseGraph(Graph graph) {
//    Graph reversed = new Graph();

    Set<GraphVertex> seen = new HashSet<>();

//    recurseReverse(graph.vertexs.get(0), null, seen);

    reverse(graph);
    return graph;
  }

  private void recurseReverse(GraphVertex current, GraphVertex previous, Set<GraphVertex> seen) {
    if (seen.contains(current)) return;

    seen.add(current);

    if (previous != null)
      previous.edges.remove(current);

    Iterator<GraphVertex> iter = current.edges.iterator();
    while (iter.hasNext()) {
      GraphVertex next = iter.next();
      recurseReverse(next, current, seen);
    }
//    for (GraphVertex vertex : current.edges) {
//      recurseReverse(vertex, current, seen);
//    }

    if (previous != null)
      current.edges.add(previous);
  }


  private Graph reverse(Graph graph){
    Graph reverseGraph = new Graph();

//    for (GraphVertex vertex : graph.vertexs) {
    for (int i = 0; i < graph.vertexs.size() ; i++) {
      GraphVertex forwardVertex = graph.vertexs.get(i);
//      GraphVertex reverseVertex = new GraphVertex(forwardVertex.val);

//      Set<GraphVertex> edges = forwardVertex.edges;
//      forwardVertex.edges = new HashSet<>();
//      for (GraphVertex edge : edges) {
//
//      }
      
      
      Iterator<GraphVertex> iter = forwardVertex.edges.iterator();
      while (iter.hasNext()) {
        GraphVertex node = iter.next();
        node.edges.add(forwardVertex);
        iter.remove();
      }
    }

    return graph;

//    for (int i = 0; i < graph.vertexs.size() ; i++) {
//      GraphVertex vertex = graph.vertexs.get(i);
//
//      int source = vertex.val;
//      for (int j = 0; j < vertex.edges.size(); j++) {
//        int destination = vertex.get(j);
//        //add reverse node
//        reverseGraph.addEdge(destination, source);
//      }
//    }
//    return reverseGraph;
  }


}

