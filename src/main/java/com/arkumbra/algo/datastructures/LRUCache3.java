package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;

public class LRUCache3 implements Cache<Integer> {

  private Map<Integer, Node> cacheByKey = new HashMap<>();

  private Node pseudoLeastRecent;
  private Node pseudoMostRecent;

  private int size;
  private int capacity;

  public LRUCache3(int capacity) {
    this.capacity = capacity;
    this.size = 0;

    pseudoLeastRecent = new Node();
    pseudoMostRecent = new Node();

    pseudoLeastRecent.moreRecent = pseudoMostRecent;
    pseudoMostRecent.lessRecent = pseudoLeastRecent;
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
      addNode(toAdd);
      cacheByKey.put(key, toAdd);
      size++;

      if (size > capacity) {
        Node leastRecent = removeLeastRecent();
        cacheByKey.remove(leastRecent.key);
        size--;
      }

    } else {
      found.value = value;
      removeNode(found);
      addNode(found);
    }
  }

  private Node removeLeastRecent() {
    Node leastRecent = pseudoLeastRecent.moreRecent;
    removeNode(leastRecent);
    return leastRecent;
  }

  private void removeNode(Node found) {
    Node prev = found.lessRecent;
    Node next = found.moreRecent;
    prev.moreRecent = next;
    next.lessRecent = prev;
  }

  private void addNode(Node found) {
    Node secondRecent = pseudoMostRecent.lessRecent;
    secondRecent.moreRecent = found;
    found.lessRecent = secondRecent;
    found.moreRecent = pseudoMostRecent;
    pseudoMostRecent.lessRecent = found;
  }

  class Node {
    int key;
    int value;

    Node lessRecent;
    Node moreRecent;
  }
}
