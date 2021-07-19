package com.arkumbra.algo.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class BreadthFirstSearchTest {

  private BreadthFirstSearch sut = new BreadthFirstSearch();

  @Test
  public void testFindAllConnectedValues() {
    GraphVertex head = new GraphVertex(3);

    // neighbours 1
    GraphVertex a = new GraphVertex(77);
    GraphVertex b = new GraphVertex(88);
    GraphVertex c = new GraphVertex(99);
    head.edges = Set.of(a, b, c);

    // neighbours 2
    GraphVertex d = new GraphVertex(7);
    GraphVertex e = new GraphVertex(8);
    GraphVertex f = new GraphVertex(9);
    d.edges.add(e);
    e.edges.add(head);
    a.edges = Set.of(d,e,f);

    // neighbours 3
    GraphVertex g = new GraphVertex(18);
    f.edges.add(g);
    g.edges.add(a);

    List<Integer> foundValuesInOrder = sut.findAllConnectedValues(head);
    System.out.println(foundValuesInOrder);
    assertEquals(8, foundValuesInOrder.size());

    assertEquals(head.val, foundValuesInOrder.get(0));

    assertOneOf(Set.of(a.val, b.val, c.val), foundValuesInOrder.get(1));
    assertOneOf(Set.of(a.val, b.val, c.val), foundValuesInOrder.get(2));
    assertOneOf(Set.of(a.val, b.val, c.val), foundValuesInOrder.get(3));

    assertOneOf(Set.of(d.val, e.val, f.val), foundValuesInOrder.get(4));
    assertOneOf(Set.of(d.val, e.val, f.val), foundValuesInOrder.get(5));
    assertOneOf(Set.of(d.val, e.val, f.val), foundValuesInOrder.get(6));

    assertEquals(g.val, foundValuesInOrder.get(7));
  }

  private void assertOneOf(Set<Integer> expected, Integer actual) {
    if (! expected.contains(actual))
      throw new AssertionError("Expected one of " + expected + " but was " + actual);
  }

}
