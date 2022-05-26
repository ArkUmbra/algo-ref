package com.arkumbra.algo.graphs;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class KosarajuSCCTest {

  private KosarajuSCC kosarajuSCC = new KosarajuSCC();

  @Test
  public void testFindSCCs() {
    int numberNodes = 5;
    int[][] edges = new int[][]{
        {0, 1}, {1, 2}, {2, 1}, {0, 3}, {3, 4}, {4, 3}
    };

    List<List<Integer>> expectedSccs = List.of(
        List.of(0),
        List.of(3, 4),
        List.of(1, 2)
    );


    List<List<Integer>> sccs = kosarajuSCC.findStronglyConnectedComponents(numberNodes, edges);
    compareSCCs(expectedSccs, sccs);
  }

  // Based off input data from:
  // https://www.programiz.com/dsa/strongly-connected-components
  @Test
  public void testFindSCCs2() {
    int numberNodes = 8;
    int[][] edges = new int[][]{
        {0, 1}, {1, 2}, {2, 3}, {3, 0}, {2, 4}, {4, 5}, {5, 6}, {6, 4}, {6, 7}
    };

    List<List<Integer>> expectedSccs = List.of(
        List.of(0, 1, 2, 3),
        List.of(4, 5, 6),
        List.of(7)
    );

    List<List<Integer>> sccs = kosarajuSCC.findStronglyConnectedComponents(numberNodes, edges);
    compareSCCs(expectedSccs, sccs);
  }

  private void compareSCCs(List<List<Integer>> expectedSccs, List<List<Integer>> sccs) {
    for (int i = 0; i < sccs.size(); i++) {
      Collections.sort(sccs.get(i));
      assertEquals(expectedSccs.get(i), sccs.get(i));
    }
  }
}
