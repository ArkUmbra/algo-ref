package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;


public class LRUCache4 implements Cache<Integer> {

  private int size;
  private int capacity;

  private Map<Integer, Node> cacheByKey = new HashMap<>();

  private Node pseudoLeastRecent;
  private Node pseudoMostRecent;

  public LRUCache4(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    this.pseudoLeastRecent = new Node();
    this.pseudoMostRecent = new Node();

    pseudoLeastRecent.moreRecent = pseudoMostRecent;
    pseudoMostRecent.lessRecent = pseudoLeastRecent;
  }

  @Override
  public Integer get(int key) {
    Node found = cacheByKey.get(key);

    if (found == null) {
      return -1;
    } else {
      removeNode(found);
      addNode(found);
      return found.value;
    }

  }

  private void addNode(Node found) {
    Node secondRecent = pseudoMostRecent.lessRecent;
    secondRecent.moreRecent = found;
    found.lessRecent = secondRecent;
    found.moreRecent = pseudoMostRecent;
    pseudoMostRecent.lessRecent = found;
  }

  private void removeNode(Node found) {
    Node prev = found.lessRecent;
    Node next = found.moreRecent;
    prev.moreRecent = next;
    next.lessRecent = prev;
  }

  private Node popLeastRecent() {
    Node leastRecent = pseudoLeastRecent.moreRecent;
//    pseudoLeastRecent.moreRecent = leastRecent.moreRecent;
//    leastRecent.moreRecent.lessRecent = pseudoLeastRecent;
    removeNode(leastRecent);
    return leastRecent;
  }

  @Override
  public void put(int key, Integer value) {
    Node found = cacheByKey.get(key);

    if (found == null) {
      Node toAdd = new Node();
      toAdd.key = key;
      toAdd.value = value;
      addNode(toAdd);
      size++;
      cacheByKey.put(key, toAdd);

      if (size > capacity) {
        Node leastRecent = popLeastRecent();
        cacheByKey.remove(leastRecent.key);
        size--;
      }
    } else {
      found.value = value;
      removeNode(found);
      addNode(found);
    }

  }

  class Node {
    int key;
    int value;
    Node lessRecent;
    Node moreRecent;
  }
}
