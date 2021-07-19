package com.arkumbra.algo.datastructures;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDirectlyFromLeetcode implements Cache<Integer> {
  private Map<Integer, DoublyLinkedNode> cacheByKey = new HashMap<>();

  private DoublyLinkedNode pseudoHead;
  private DoublyLinkedNode pseudoTail;

  private int size;
  private int capacity;

  public LRUCacheDirectlyFromLeetcode(int capacity) {
    this.size = 0;
    this.capacity = capacity;

    this.pseudoHead = new DoublyLinkedNode();
    this.pseudoTail = new DoublyLinkedNode();

    this.pseudoHead.next = this.pseudoTail;
    this.pseudoTail.prev = this.pseudoHead;
  }


  public Integer get(int key) {
    DoublyLinkedNode found = cacheByKey.get(key);
    if (found == null) return -1;

    moveToHead(found);
    return found.value;
  }


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
        DoublyLinkedNode tail = popTail();
        cacheByKey.remove(tail.key);
        size--;
      }
    } else {
      found.value = value;
      moveToHead(found);
    }

  }

  private void add(DoublyLinkedNode doublyLinkedNode) {
    // DoublyLinkedNode oldHead = pseudoHead.prev;
    // oldHead.next = doublyLinkedNode;
    // doublyLinkedNode.prev = oldHead;
    // doublyLinkedNode.next = pseudoHead;

    doublyLinkedNode.prev = pseudoHead;
    doublyLinkedNode.next = pseudoHead.next;
    pseudoHead.next.prev = doublyLinkedNode;
    pseudoHead.next = doublyLinkedNode;
  }

  private DoublyLinkedNode popTail() {
    // DoublyLinkedNode toRemove = pseudoTail.next;
    DoublyLinkedNode toRemove = pseudoTail.prev;
    removeNode(toRemove);
    return toRemove;
  }

  private void removeNode(DoublyLinkedNode doublyLinkedNode) {
    DoublyLinkedNode prev = doublyLinkedNode.prev;
    DoublyLinkedNode next = doublyLinkedNode.next;

    prev.next = next;
    next.prev = prev;
  }

  private void moveToHead(DoublyLinkedNode node) {
    removeNode(node);
    add(node);
  }


  class DoublyLinkedNode {
    DoublyLinkedNode next;
    DoublyLinkedNode prev;

    Integer key;
    Integer value;
  }
}
