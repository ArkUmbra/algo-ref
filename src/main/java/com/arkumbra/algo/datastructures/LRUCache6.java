package com.arkumbra.algo.datastructures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache6 extends LinkedHashMap<Integer, Integer> implements Cache<Integer> {

  private final int capacity;

  public LRUCache6(int capacity) {
    super(capacity, 0.75f, true);
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    return super.getOrDefault(key, -1);
  }

  @Override
  public void put(int key, Integer value) {
    super.put(key, value);
  }

  @Override
  public boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
    return (size() > capacity);
  }

}
