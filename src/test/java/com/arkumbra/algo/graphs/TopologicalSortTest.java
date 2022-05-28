package com.arkumbra.algo.graphs;

import com.arkumbra.algo.graphs.TopologicalSort.Vertex;
import java.util.List;
import org.junit.Test;

public class TopologicalSortTest {

  // Based on Figure 22.7, page 613 of Introduction to Algorithms, 3rd Edition

  // 0      1     2       3     4         5           6       7     8
  // shirt, tie, jacket, belt, trousers, undershorts, shoes, socks, watch
  @Test
  public void testClothing() {
    Vertex<String> shirt = new Vertex<>("shirt", List.of(1, 3));
    Vertex<String> tie = new Vertex<>("tie", List.of(2));
    Vertex<String> jacket = new Vertex<>("jacket", List.of());
    Vertex<String> belt = new Vertex<>("belt", List.of(2));
    Vertex<String> trousers = new Vertex<>("trousers", List.of(3, 6));
    Vertex<String> undershorts = new Vertex<>("undershorts", List.of(4, 6));
    Vertex<String> shoes = new Vertex<>("shoes", List.of());
    Vertex<String> socks = new Vertex<>("socks", List.of(6));
    Vertex<String> watch = new Vertex<>("watch", List.of());
    List<Vertex<String>> graph = List.of(shirt, tie, jacket, belt, trousers, undershorts, shoes, socks, watch);

    List<String> sorted = new TopologicalSort<String>().sort(graph);
    System.out.println(sorted);
  }

  @Test
  public void testSortSimplified() {
    int totalNodes = 5;
    List<int[]> edges = List.of(
        new int[]{0, 1},
        new int[]{1, 2},
        new int[]{3, 2}
    );
    List<Integer> top = new TopologicalSort<Integer>().sortSimplified(totalNodes, edges);
    System.out.println(top);
  }

}
