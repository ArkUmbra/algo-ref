package com.arkumbra.algo.datastructures;

import java.util.HashMap;

public class LRUCache5 implements Cache<Integer>  {

  private HashMap<Integer, Node> cacheByKey = new HashMap<>();
  private Node dummyLeastRecent;
  private Node dummyMostRecent;
  private int capacity;
  private int size;

  public LRUCache5(int capacity) {
    this.capacity = capacity;
    this.size = 0;

    this.dummyLeastRecent = new Node();
    this.dummyMostRecent = new Node();

    this.dummyLeastRecent.moreRecent = dummyMostRecent;
    this.dummyMostRecent.lessRecent = dummyLeastRecent;
  }

  @Override
  public Integer get(int key) {
    Node found = cacheByKey.get(key);

    if (found == null) {
      return -1;
    } else {
      remove(found);
      add(found);
      return found.value;
    }
  }

  private void add(Node node) {
    Node secondRecent = dummyMostRecent.lessRecent;
    secondRecent.moreRecent = node;
    node.lessRecent = secondRecent;
    node.moreRecent = dummyMostRecent;
    dummyMostRecent.lessRecent = node;
  }

  private void remove(Node node) {
    Node prev = node.lessRecent;
    Node next = node.moreRecent;
    prev.moreRecent = next;
    next.lessRecent = prev;
  }

  private Node popLeastRecent() {
    Node node = dummyLeastRecent.moreRecent;
    remove(node);
    return node;
  }

  @Override
  public void put(int key, Integer value) {
    Node found = cacheByKey.get(key);
    if (found == null) {
      Node toAdd = new Node();
      toAdd.key = key;
      toAdd.value = value;
      add(toAdd);
      cacheByKey.put(key, toAdd);
      size++;

      if (size > capacity) {
        Node leastRecent = popLeastRecent();
        cacheByKey.remove(leastRecent.key);
        size--;
      }
    } else {
      found.value = value;
      remove(found);
      add(found);
    }
  }

  class Node {
    Node lessRecent;
    Node moreRecent;
    int key;
    int value;
  }
}
