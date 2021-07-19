package com.arkumbra.algo.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache implements Cache<Integer> {

  private Map<Integer, DoublyLinkedNode> cacheByKey = new HashMap<>();

  private DoublyLinkedNode pseudoLeastRecent;
  private DoublyLinkedNode pseudoMostRecent; // recently used

  private int size;
  private int capacity;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    this.pseudoLeastRecent = new DoublyLinkedNode();
    this.pseudoMostRecent = new DoublyLinkedNode();

    this.pseudoLeastRecent.moreRecent = this.pseudoMostRecent;
    this.pseudoMostRecent.lessRecent = this.pseudoLeastRecent;
  }

  @Override
  public Integer get(int key) {
    DoublyLinkedNode found = cacheByKey.get(key);
    if (found == null) return -1;

    moveToHead(found);
    return found.value;
  }

  @Override
  public void put(int key, Integer value) {
    DoublyLinkedNode found = cacheByKey.get(key);

    if (found == null) {
      DoublyLinkedNode newNode = new DoublyLinkedNode();
      newNode.key = key;
      newNode.value = value;

      cacheByKey.put(key, newNode);
      add(newNode);
      size++;

      if (size > capacity) {
        DoublyLinkedNode leastRecentlyUsed = popLeastUsed();
        cacheByKey.remove(leastRecentlyUsed.key);
        size--;
      }
    } else {
      found.value = value;
      moveToHead(found);
    }

  }

  private void add(DoublyLinkedNode doublyLinkedNode) {
//    DoublyLinkedNode oldHead = pseudoBase.prev;
//    oldHead.next = doublyLinkedNode;
//    doublyLinkedNode.prev = oldHead;
//    doublyLinkedNode.next = pseudoBase;

    DoublyLinkedNode secondRecent = pseudoMostRecent.lessRecent;
    secondRecent.moreRecent = doublyLinkedNode;
    doublyLinkedNode.lessRecent = secondRecent;
    pseudoMostRecent.lessRecent = doublyLinkedNode;
  }

  private DoublyLinkedNode popLeastUsed() {
    DoublyLinkedNode toRemove = pseudoLeastRecent.moreRecent;
    removeNode(toRemove);
    return toRemove;
  }

  private void removeNode(DoublyLinkedNode doublyLinkedNode) {
    DoublyLinkedNode prev = doublyLinkedNode.lessRecent;
    DoublyLinkedNode next = doublyLinkedNode.moreRecent;

    prev.moreRecent = next;
    next.lessRecent = prev;
  }

  private void moveToHead(DoublyLinkedNode node) {
    removeNode(node);
    add(node);
  }


  class DoublyLinkedNode {
    DoublyLinkedNode moreRecent;
    DoublyLinkedNode lessRecent;

    Integer key;
    Integer value;
  }
}
