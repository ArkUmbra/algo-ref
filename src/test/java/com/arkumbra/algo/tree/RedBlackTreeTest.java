package com.arkumbra.algo.tree;

import org.junit.Test;

public class RedBlackTreeTest {

  @Test
  public void testSimple() {
    RedBlackTree<Integer, Integer> rbt = new RedBlackTree<>();

    rbt.put(10, 100);
    rbt.put(1, 50);
    rbt.put(111, 150);
    rbt.put(112, 151);
    System.out.println(rbt.toString());
  }

}
