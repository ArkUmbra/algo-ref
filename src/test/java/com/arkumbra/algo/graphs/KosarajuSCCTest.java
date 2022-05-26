package com.arkumbra.algo.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        list(0),
        list(3, 4),
        list(1, 2)
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
        list(0, 1, 2, 3),
        list(4, 5, 6),
        list(7)
    );

    List<List<Integer>> sccs = kosarajuSCC.findStronglyConnectedComponents(numberNodes, edges);
    compareSCCs(expectedSccs, sccs);
  }

  @Test
  public void testFindSCCsForDisconnectedItems() {
    int numberNodes = 4;
    int[][] edges = new int[][]{
        {0, 1}, {1, 0}, {2, 3}, {3, 2}
    };

    List<List<Integer>> expectedSccs = List.of(
        list(0, 1),
        list(2, 3)
    );

    List<List<Integer>> sccs = kosarajuSCC.findStronglyConnectedComponents(numberNodes, edges);
    compareSCCs(expectedSccs, sccs);
  }

  private List<Integer> list(int... nums) {
    List<Integer> res = new ArrayList<>();
    for (int num : nums)
      res.add(num);
    return res;
  }

  private void compareSCCs(List<List<Integer>> expectedSccs, List<List<Integer>> sccs) {
    assertEquals(expectedSccs.size(), sccs.size());

    Set<List<Integer>> expected = new HashSet<>();

    for (int i = 0; i < expectedSccs.size(); i++) {
      Collections.sort(expectedSccs.get(i));
      expected.add(expectedSccs.get(i));
    }

    for (int i = 0; i < sccs.size(); i++) {
      Collections.sort(sccs.get(i));
      assertTrue(expected.contains(sccs.get(i)));
      expected.remove(sccs.get(i));
    }

    assertTrue("Size is " + expected.size(), expected.isEmpty());
  }


}
