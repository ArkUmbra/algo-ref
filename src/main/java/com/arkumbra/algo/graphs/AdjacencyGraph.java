package com.arkumbra.algo.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class AdjacencyGraph {
//  private final int noOfVertices;
//
////  private List<Node> graph = new ArrayList<>();
//  private List<Node> graph;
//
//  public AdjacencyGraph(int noOfVertices) {
//    this.noOfVertices = noOfVertices;
//    this.graph = new ArrayList<>(noOfVertices);
//  }
//
//  public void addEdge(int from, int to) {
////    Node fromNode = new Node(from);
////    Node toNode = new Node(to);
////
////    vertices.add(fromNode);
////    vertices.add(toNode);
//
//    graph.get(from).adjacent.add(to);
//  }
//
//
//  public void printGraph() {
//    for (int i = 0; i < noOfVertices; i++) {
//      System.out.print(i + "--> ");
//      for (int j = 0; j < adj.get(i).size(); j++)
//        System.out.print(adj.get(i).get(j).value + " ");
//      System.out.println();
//    }
//  }
//
//
//  public AdjacencyGraph buildTranspose() {
//    AdjacencyGraph transpose = new AdjacencyGraph(noOfVertices);
//
//    // Traverse the graph and for each edge u, v
//    // in graph add the edge v, u in transpose
//    for (int i = 0; i < noOfVertices; i++)
//      for (int j = 0; j < adj.get(i).size(); j++)
//        transpose.addEdge(adj.get(i).get(j).value, i);
//
//    return transpose;
//  }
//
//  public int getNoOfVertices() {
//    return noOfVertices;
//  }
//
//  public Set<Node> getVertices() {
//    return vertices;
//  }
//
//  public List<List<Node>> getAdj() {
//    return adj;
//  }


  private final int noOfVertices;

  private List<List<Integer>> adj;

  public AdjacencyGraph(int vertices) {
    this.noOfVertices = vertices;

    this.adj = new ArrayList<>();

    for (int i = 0; i < vertices; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int from, int to) {
    adj.get(from).add(to);
  }


  public void printGraph() {
    for (int i = 0; i < noOfVertices; i++) {
      System.out.print(i + "--> ");
      for (int j = 0; j < adj.get(i).size(); j++)
        System.out.print(adj.get(i).get(j) + " ");
      System.out.println();
    }
  }

  public AdjacencyGraph buildTranspose() {
    AdjacencyGraph transpose = new AdjacencyGraph(noOfVertices);

    // Traverse the graph and for each edge u, v
    // in graph add the edge v, u in transpose
    for (int i = 0; i < noOfVertices; i++)
      for (int j = 0; j < adj.get(i).size(); j++)
        transpose.addEdge(adj.get(i).get(j), i);

    return transpose;
  }

  public int getNoOfVertices() {
    return noOfVertices;
  }

  public List<List<Integer>> getAdj() {
    return adj;
  }

  public Queue<Integer> findStronglyConnectedComponents() {
    Stack<Integer> stack = new Stack<>();

    boolean[] visited = new boolean[noOfVertices];


    for (int i = 0; i < noOfVertices; i++) {
      if (! visited[i]) fillOrder(i, visited, stack);
    }
//    Stack<Integer> vertexStack = new Stack<>();
//    vertexStack.push(0);
//    for (int i = 0; i < noOfVertices; i++) {
//      if (! visited[i]) fillOrder(vertexStack, visited, stack);
//    }

    AdjacencyGraph reversed = this.buildTranspose();
    visited = new boolean[noOfVertices];

    Queue<Integer> sccSizes = new PriorityQueue<>(Comparator.comparingInt(l -> -l));
    while (! stack.empty()) {
      int v = stack.pop();
//      int v = stack.peek();

      if (! visited[v]) {
        SCC scc = new SCC();
        reversed.dfsUtil(v, visited, scc);
//        reversed.dfsUtil(stack, visited, scc);
        sccSizes.add(scc.nodes.size());
//        System.out.println();
      }
    }

//    while (! sccSizes.isEmpty()) {
//      System.out.println(sccSizes.remove());
//    }
//    System.out.println(sccSizes);

    return sccSizes;
  }



  private void fillOrder(Integer vertex, boolean[] visited, Stack<Integer> order) {
    visited[vertex] = true;

    for (Integer n : adj.get(vertex)) {
      if (! visited[n]) {
        fillOrder(n, visited, order);
      }
    }

    order.push(vertex);
  }

  private void fillOrder(Stack<Integer> dfsStack, boolean[] visited, Stack<Integer> order) {
    while (! dfsStack.isEmpty()) {
      int vertex = dfsStack.pop();
      if (visited[vertex]) continue;

      visited[vertex] = true;

      for (Integer edge : adj.get(vertex)) {
        dfsStack.push(edge);
      }

      order.push(vertex);
    }
  }

  private void dfsUtil(int v, boolean[] visited, SCC scc) {
    visited[v] = true;
    scc.nodes.add(v);
//    System.out.print(v + " ");

    for (Integer n : adj.get(v)) {
      if (! visited[n]) {
        dfsUtil(n, visited, scc);
      }
    }
  }

  private void dfsUtil(Stack<Integer> vStack, boolean[] visited, SCC scc) {
    while (! vStack.isEmpty()) {
      Integer v = vStack.pop();
      visited[v] = true;
      scc.nodes.add(v);

      for (Integer n : adj.get(v)) {
        if (! visited[n]) {
          vStack.push(n);
        }
      }

    }
  }

}

class SCC {
  public List<Integer> nodes =new ArrayList<>();
}

class Node {
  public int value;
  public boolean explored = false;

  public Node(int value) {
    this.value = value;
  }
}

//class Node {
////  public int value;
//  public List<Integer> adjacent = new ArrayList<>();

//  public Node(int value) {
//    this.value = value;
//  }

