package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache4 implements Cache<Integer> {

  private final int capacity;
  private int currentMin = -1;

  private Map<Integer, Integer> valueByKey = new HashMap<>();
  private Map<Integer, Integer> countByKey = new HashMap<>();
  private Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>(); // stores keys, not values

  public LFUCache4(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;

    int useCount = countByKey.get(key);
    // remove key from current LRU, we'll move it
    lruByCount.get(useCount).remove(key);

    if (useCount == currentMin && lruByCount.get(currentMin).isEmpty()) currentMin++;

    putCountAndUpdateLRU(key, useCount + 1);
    return valueByKey.get(key);
  }

  private void putCountAndUpdateLRU(int key, int count) {
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
      // update count by 1
      get(key);
      return;
    }

    // if no space
    if (valueByKey.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next();
      deleteMinByKey(keyToDelete);
    }

    // new key, so first time it is used
    currentMin = 1;
    putCountAndUpdateLRU(key, currentMin);
    valueByKey.put(key, value);
  }

  private void deleteMinByKey(int keyToDelete) {
    valueByKey.remove(keyToDelete);
    countByKey.remove(keyToDelete);
    lruByCount.get(currentMin).remove(keyToDelete);
  }
}
