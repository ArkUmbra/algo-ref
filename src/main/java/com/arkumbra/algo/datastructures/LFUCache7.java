package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache7 implements Cache<Integer> {

  private final int capacity;
  private int currentMin;

  private Map<Integer, Integer> valueByKey = new HashMap<>();
  private Map<Integer, Integer> countByKey = new HashMap<>();
  private Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();

  public LFUCache7(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;

    int currentCount = countByKey.get(key);
    // remove for now, we'll move to a different count
    lruByCount.get(currentCount).remove(key);

    if (currentCount == currentMin && lruByCount.get(currentCount).isEmpty()) currentMin++;

    updateCountAndLRU(key, ++currentCount);
    return valueByKey.get(key);
  }

  private void updateCountAndLRU(int key, int count) {
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
      // Update the count
      get(key);
      return;
    }

    if (valueByKey.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next();
      deleteMinByKey(keyToDelete);
    }

    // This is now a new key
    currentMin = 1;
    updateCountAndLRU(key, currentMin);
    valueByKey.put(key, value);
  }

  private void deleteMinByKey(int key) {
    valueByKey.remove(key);
    countByKey.remove(key);
    lruByCount.get(currentMin).remove(key);
  }
}
