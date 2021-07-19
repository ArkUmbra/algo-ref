package com.arkumbra.algo.graphs;

import java.util.Set;
import org.junit.Test;

public class StronglyConnectedComponentFinderTest {

  GraphVertex head;
  GraphVertex a;
  GraphVertex b;
  GraphVertex c;
  GraphVertex d;
  GraphVertex e;
  GraphVertex f;

  private StronglyConnectedComponentFinder sut = new StronglyConnectedComponentFinder();
  private DepthFirstSearch depthFirstSearch = new DepthFirstSearch();

  @Test
  public void reverseGraph() {
    Graph graph = buildGraph(new Graph());

    printConnections(graph);
    System.out.println(depthFirstSearch.findAllConnectedValuesRecursive(graph));

    sut.reverseGraph(graph);
    System.out.println(depthFirstSearch.findAllConnectedValuesRecursive(graph));
    printConnections(graph);
  }


//  @Test
//  public void reverseCyclicalConnectedGraph() {
//    Graph graph = buildGraph(new Graph());
//
//    System.out.println(depthFirstSearch.findAllConnectedValuesRecursive(graph));
//    sut.reverseGraph(graph);
//
//    System.out.println(depthFirstSearch.findAllConnectedValuesRecursive(graph));
//  }

  private Graph buildGraph(Graph graph) {
    head = new GraphVertex(3);

    // neighbours 1
    a = new GraphVertex(77);
    b = new GraphVertex(88);
    head.edges.addAll(Set.of(a, b));

    // neighbours 2
    c = new GraphVertex(7);
    d = new GraphVertex(8);
    a.edges.add(c);
    b.edges.add(d);

    // neighbours 3
    e = new GraphVertex(18);
    f = new GraphVertex(19);
    c.edges.add(e);
    d.edges.add(f);
    e.edges.add(head);
    f.edges.add(head);

    graph.vertexs.addAll(Set.of(head, a, b, c, d, e, f));

    return graph;
  }

  private void printConnections(Graph graph) {
    for (GraphVertex vertex : graph.vertexs) {
      StringBuilder connections = new StringBuilder();
      vertex.edges.forEach(e -> connections.append(e).append(", "));
      System.out.println(vertex.val + "-> " + connections);
    }
  }


  @Test
  public void findStronglyConnectedComponentsOfSmallGraph() {
    GraphVertex graphVertex = new GraphVertex(1);

  }

  /*
   Based on Coursera assignment here

   https://www.coursera.org/learn/algorithms-graphs-data-structures/exam/rOtFq/programming-assignment-1/
   */
  @Test
  public void findStronglyConnectedComponentsOfEnormousGraph() {

  }



}
