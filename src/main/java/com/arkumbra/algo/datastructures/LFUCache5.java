package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache5 implements Cache<Integer> {

  private final int capacity;
  private int currentMin = -1;

  private final Map<Integer, Integer> valueByKey = new HashMap<>();
  private final Map<Integer, Integer> countByKey = new HashMap<>();
  private final Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();

  public LFUCache5(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) {
      return -1;
    }

    int useCount = countByKey.get(key);
    // remove current count, and add again later
    lruByCount.get(useCount).remove(key);

    if (currentMin == useCount && lruByCount.get(useCount).isEmpty()) currentMin++;

    putCountAndUpdateLRU(key, useCount+1);

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
      get(key); // Update count value
      return;
    }

    if (valueByKey.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next(); // least used
      deleteMinByKeyAndUpdateCounts(keyToDelete);
    }

    // new key, so reset min
    currentMin = 1;
    putCountAndUpdateLRU(key, currentMin);
    valueByKey.put(key, value);
  }

  private void deleteMinByKeyAndUpdateCounts(int key) {
    valueByKey.remove(key);
    countByKey.remove(key);
    lruByCount.get(currentMin).remove(key);
  }
}
