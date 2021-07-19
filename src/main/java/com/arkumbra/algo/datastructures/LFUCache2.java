package com.arkumbra.algo.datastructures;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache2 implements Cache<Integer> {

  private int currentMin;
  private int capacity;

  private Map<Integer, Integer> valueByKey = new HashMap<>();
  private Map<Integer, Integer> countByKey = new HashMap<>();
  private Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();

  public LFUCache2(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;

    int itemCount = countByKey.get(key);
    LinkedHashSet<Integer> lruWithThisCount = lruByCount.get(itemCount);
    lruWithThisCount.remove(key);

    if (itemCount == currentMin && lruByCount.get(currentMin).isEmpty()) currentMin++;

    putCount(key, itemCount + 1);
    return valueByKey.get(key);
  }

  private void putCount(int key, int count) {
    countByKey.put(key, count);
    if (! lruByCount.containsKey(count)) {
      lruByCount.put(count, new LinkedHashSet<>());
    }

    lruByCount.get(count).add(key);
  }

  @Override
  public void put(int key, Integer value) {
    if (capacity <= 0) return;

    if (valueByKey.containsKey(key)) {
      valueByKey.put(key, value);
      get(key); // update key count
      return;
    }

    // Need to make space
    if (valueByKey.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next();
      evictMinByKey(keyToDelete);
    }

    // If we got here, this key is new
    currentMin = 1;
    putCount(key, currentMin);
    valueByKey.put(key, value);
  }

  private void evictMinByKey(int keyToDelete) {
    lruByCount.get(currentMin).remove(keyToDelete);
    valueByKey.remove(keyToDelete);
    countByKey.remove(keyToDelete);
  }
}
