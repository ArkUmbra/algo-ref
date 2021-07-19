package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;

public class LRUCache2 implements Cache<Integer> {

  private Map<Integer, Node> cacheByKey = new HashMap<>();

  private int size;
  private int capacity;

  private Node pseudoLeastRecent;
  private Node pseudoMostRecent;

  public LRUCache2(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    this.pseudoLeastRecent = new Node();
    this.pseudoMostRecent = new Node();

    this.pseudoLeastRecent.moreRecent = this.pseudoMostRecent;
    this.pseudoMostRecent.lessRecent = this.pseudoLeastRecent;
  }

  @Override
  public Integer get(int key) {
    Node found = cacheByKey.get(key);
    if (found == null) return -1;

    removeNode(found);
    addNode(found);

    return found.value;
  }

  @Override
  public void put(int key, Integer value) {
    Node found = cacheByKey.get(key);

    if (found == null) {
      Node toAdd = new Node();
      toAdd.key = key;
      toAdd.value = value;
      cacheByKey.put(key, toAdd);
      addNode(toAdd);
      size++;

      if (size > capacity) {
        Node leastRecent = removeLeastRecent();
        cacheByKey.remove(leastRecent.key);
        size--;
      }

    } else {
      removeNode(found);
      addNode(found);
      found.value = value;
    }
  }

  private void removeNode(Node node) {
    Node prev = node.lessRecent;
    Node next = node.moreRecent;

    prev.moreRecent = next;
    next.lessRecent = prev;
  }

  private void addNode(Node node) {
    Node secondRecent = pseudoMostRecent.lessRecent;
    secondRecent.moreRecent = node;
    node.lessRecent = secondRecent;
    pseudoMostRecent.lessRecent = node;
  }

  private Node removeLeastRecent() {
    Node leastRecent = pseudoLeastRecent.moreRecent;
    removeNode(leastRecent);
    return leastRecent;
  }


  class Node {
    int key;
    int value;

    Node moreRecent;
    Node lessRecent;
  }
}
