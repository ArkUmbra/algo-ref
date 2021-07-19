package com.arkumbra.algo.graphs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Queue;
import org.junit.Test;

public class AdjacencyGraphTest {

  @Test
  public void test() {
    AdjacencyGraph graph = new AdjacencyGraph(5);
    graph.addEdge(0, 1);
    graph.addEdge(0, 4);
    graph.addEdge(0, 3);
    graph.addEdge(2, 0);
    graph.addEdge(3, 2);
    graph.addEdge(4, 1);
    graph.addEdge(4, 3);

    System.out.println("Forward");
    graph.printGraph();

    AdjacencyGraph transpose = graph.buildTranspose();
    System.out.println("Transpose");
    transpose.printGraph();

    AdjacencyGraph forwardAgain = transpose.buildTranspose();
    System.out.println("Forward");
    forwardAgain.printGraph();
  }

  @Test
  public void testFindSCC() {
    AdjacencyGraph g = new AdjacencyGraph(5);
    g.addEdge(1, 0);
    g.addEdge(0, 2);
    g.addEdge(2, 1);
    g.addEdge(0, 3);
    g.addEdge(3, 4);

    g.findStronglyConnectedComponents();
  }

//  @Test
//  public void find5BiggestSCCSsmall() throws IOException {
//    AdjacencyGraph graph = new AdjacencyGraph(8);
//    Files.lines(Path.of("src/test/resources/sccSmall.txt")).forEach(line -> {
//      String[] parts = line.split(" ");
//      Integer from = Integer.valueOf(parts[0]);
//      graph.addEdge(parts[0], parts[]);
//    });
//  }

  @Test
  public void find5BiggestSCCS() throws IOException {
    int nBiggest = 5;
    AdjacencyGraph graph = new AdjacencyGraph(875715);

    Files.lines(Path.of("src/test/resources/SCC.txt")).forEach(line -> {
      String[] parts = line.split(" ");
      int from = Integer.parseInt(parts[0]);
      int to = Integer.parseInt(parts[1]);
      graph.addEdge(from, to);
    });

    Queue<Integer> biggest = graph.findStronglyConnectedComponents();
    String[] answers = new String[nBiggest];
    for (int i = 0; i < nBiggest; i++) {
      answers[i] = "" + biggest.remove();
    }

    System.out.println(String.join(",", answers));
// 434821,968,459,313,211
  }


}
