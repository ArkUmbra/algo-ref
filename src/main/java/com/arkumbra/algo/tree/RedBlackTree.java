package com.arkumbra.algo.tree;

/*
 Based on https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class RedBlackTree<KEY extends Comparable<KEY>, VALUE> {
  private static boolean RED = true;
  private static boolean BLACK = false;

  private Node root = null;


  // ------------------------------------------------
  // helper methods

  private boolean isRed(Node node) {
    if (node == null) return false;
    return node.colour == RED;
  }

  private boolean isBlack(Node node) {
    return !isRed(node);
  }

  private int getSize(Node node) {
    if (node == null) return 0;
    return node.size;
  }

  public int size() {
    return getSize(root);
  }

  public boolean isEmpty() {
    return root == null;
  }

  // ------------------------------------------------
  // Standard BST search
  // ------------------------------------------------


  public VALUE get(KEY key) {
    return get(root, key);
  }

  private VALUE get(Node node, KEY key) {
    while (node != null) {
      int compare = key.compareTo(node.key);
      if      (compare < 0) node = node.left;
      else if (compare > 0) node = node.right;
      else return node.value;
    }
    return null;
  }

  public boolean contains(KEY key) {
    return get(key) != null;
  }

  // ------------------------------------------------
  // Red Black insertion stuff
  // ------------------------------------------------

  public void put(KEY key, VALUE value) {
    if (key == null) throw new IllegalArgumentException("Key must not be null");
    // In the original paper I read, they didn't throw here, but hey...
    if (value == null) throw new IllegalArgumentException("Value must not be null. Use remove instead");

    root = put(root, key, value);
    root.colour = BLACK;
  }

  private Node put(Node node, KEY key, VALUE value) {
    if (node == null) return new Node(key, value, RED, 1);

    // TODO read through these steps again
    int compare = key.compareTo(node.key);
    if      (compare < 0) node.left = put(node.left, key, value);
    else if (compare > 0) node.right = put(node.right, key, value);
    else                  node.value = value;

    // TODO can this block be replaced by a single call to balance()?
    // 'Fix up any right-leaning links'
    if (isRed(node.right) && isBlack(node.left))      node = rotateLeft(node);
    if (isRed(node.left)  && isRed(node.left.left))   node = rotateRight(node);
    if (isRed(node.left)  && isRed(node.right))       flipColoursOfNodeAndTwoChildren(node);
    node.size = getSize(node.left) + getSize(node.right) + 1;

    return node;
  }

  // ------------------------------------------------
  // Red Black delete
  // ------------------------------------------------

  public void deleteMin() {
    if (isEmpty()) throw new NoSuchElementException("No elements to delete");

    // if both children of the root are black, set root to red
    // TODO why? To keep the colouring consistent? Why do we do this at delete time?
    if (isBlack(root.left) && isBlack(root.right)) {
      // TODO What is the point of setting this here if we're just going to set it to black after?
      root.colour = RED;
    }

    root = deleteMin(root);
    // TODO why set the colour here? Is it because root and level 1 can both be black at the same time?
    if (! isEmpty()) root.colour = BLACK;
  }

  // Returns the new root of this node, after deletion
  private Node deleteMin(Node node) {
    if (node.left == null)
      return null;

    if (isBlack(node.left) && isBlack(node.left.left))
      node = moveRedLeft(node);

    node.left = deleteMin(node.left);
    return balance(node);
  }




  // ------------------------------------------------
  // Red Black helper methods
  // ------------------------------------------------

  // TODO work through what is happening here
  private Node rotateRight(Node node) {
    Node rotated = node.left;
    node.left = rotated.right;
    rotated.right = node;
    rotated.colour = rotated.right.colour;
    rotated.right.colour = RED;
    rotated.size = node.size;
    node.size = getSize(node.left) + getSize(node.right) + 1;
    return rotated;
  }

  private Node rotateLeft(Node node) {
    Node rotated = node.right;
    node.right = rotated.left;
    rotated.left = node;
    rotated.colour = rotated.left.colour;
    rotated.left.colour = RED;
    rotated.size = node.size;
    node.size = getSize(node.left) + getSize(node.right) + 1;
    return rotated;
  }

  private void flipColoursOfNodeAndTwoChildren(Node node) {
    node.colour = !node.colour;
    node.left.colour = !node.left.colour;
    node.right.colour = !node.right.colour;
  }

  private Node moveRedLeft(Node node) {
    // TODO why do we flip colours twice in this method?
    flipColoursOfNodeAndTwoChildren(node);

    if (isRed(node.right.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      flipColoursOfNodeAndTwoChildren(node);
    }

    return node;
  }

  // restore red-black tree invariant
  // TODO what does 'invariant' mean here?
  private Node balance(Node node) {
    if (isRed(node.right) && isBlack(node.left))      node = rotateLeft(node);
    if (isRed(node.left)  && isRed(node.left.left))   node = rotateRight(node);
    if (isRed(node.left)  && isRed(node.right))       flipColoursOfNodeAndTwoChildren(node);
    node.size = getSize(node.left) + getSize(node.right) + 1;

    return node;
  }


  @Override
  public String toString() {
    Map<Integer, List<String>> treeByLevel = new HashMap<>();
    buildString(treeByLevel, root, 0);

    StringBuilder sb = new StringBuilder();
    for (Map.Entry<Integer, List<String>> entry : treeByLevel.entrySet()) {
      for (String s : entry.getValue()) {
        sb.append(s);
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  private void buildString(Map<Integer, List<String>> treeByLevel, Node node, int level) {
    treeByLevel.computeIfAbsent(level, k -> new ArrayList<>());

    String res;
    if (node == null) res = "LEAF";
    else res = (isRed(node) ? "R" : "B") + node.key + ":" + node.value;

    treeByLevel.get(level).add(res);
    if (node == null) return;
    buildString(treeByLevel, node.left, level+1);
    buildString(treeByLevel, node.right, level+1);
  }

  class Node {
    KEY key;
    VALUE value;
    Node left;
    Node right;
    boolean colour;
    int size; // subtree count

    public Node(KEY key, VALUE value, Boolean colour, int size) {
      this.key = key;
      this.value = value;
      this.colour = colour;
      this.size = size;
    }
  }
}
