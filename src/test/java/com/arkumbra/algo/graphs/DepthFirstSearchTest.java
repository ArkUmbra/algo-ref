package com.arkumbra.algo.graphs;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class DepthFirstSearchTest {

  GraphVertex head;
  GraphVertex a;
  GraphVertex b;
  GraphVertex c;
  GraphVertex d;
  GraphVertex e;
  GraphVertex f;

  private DepthFirstSearch sut = new DepthFirstSearch();

  @Test
  public void testFindAllConnectedValues() {
    GraphVertex graphVertex = buildGraph();

    List<Integer> foundValuesInOrder = sut.findAllConnectedValues(graphVertex);

    assertContents(foundValuesInOrder);
  }

  @Test
  public void testFindAllConnectedValuesRecursive() {
    GraphVertex graphVertex = buildGraph();

    List<Integer> foundValuesInOrder = sut.findAllConnectedValuesRecursive(graphVertex);

    assertContents(foundValuesInOrder);
  }


  private GraphVertex buildGraph() {
    head = new GraphVertex(3);

    // neighbours 1
    a = new GraphVertex(77);
    b = new GraphVertex(88);
    head.edges = Set.of(a, b);

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

    return head;
  }

  private void assertContents(List<Integer> foundValuesInOrder) {
    System.out.println(foundValuesInOrder);
    assertEquals(7, foundValuesInOrder.size());

    // Should dive all the way into path first, before parsing neighbours.

    assertEquals(head.val, foundValuesInOrder.get(0));

    assertOneOf(Set.of(a.val, b.val), foundValuesInOrder.get(1));

    // Depending on which neighbour was processed, we expected a different path

    // if went from head to a
    if (foundValuesInOrder.get(1).equals(a.val)) {
      // a connects to c
      assertEquals(c.val, foundValuesInOrder.get(2));
      // c connects to e
      assertEquals(e.val, foundValuesInOrder.get(3));

      // go back to head and explore next neighbour - should be b
      // head connects to b
      assertEquals(b.val, foundValuesInOrder.get(4));
      // b connects to d
      assertEquals(d.val, foundValuesInOrder.get(5));
      // d connects to f
      assertEquals(f.val, foundValuesInOrder.get(6));

    } else {
      assertEquals(b.val, foundValuesInOrder.get(1));
      assertEquals(d.val, foundValuesInOrder.get(2));
      assertEquals(f.val, foundValuesInOrder.get(3));

      assertEquals(a.val, foundValuesInOrder.get(4));
      assertEquals(c.val, foundValuesInOrder.get(5));
      assertEquals(e.val, foundValuesInOrder.get(6));
    }
  }

  private void assertOneOf(Set<Integer> expected, Integer actual) {
    if (! expected.contains(actual)) 
      throw new AssertionError("Expected one of " + expected + " but was " + actual);
  }

}
