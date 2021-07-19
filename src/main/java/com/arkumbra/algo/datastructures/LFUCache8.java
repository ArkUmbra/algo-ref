package com.arkumbra.algo.datastructures;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache8 implements Cache<Integer> {

  private final int capacity;
  private int currentMin;

  private final Map<Integer, Integer> valueByKey = new HashMap<>();
  private final Map<Integer, Integer> countByKey = new HashMap<>();
  private final Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();

  public LFUCache8(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;

    int currentCount = countByKey.get(key);
    // Remove this element for now, because we will update its count
    lruByCount.get(currentCount).remove(key);

    if (currentCount == currentMin && lruByCount.get(currentCount).isEmpty()) currentMin++;

    putCountAndUpdateLRU(key, ++currentCount);

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
      get(key); // Update read count for key
    }

    if (lruByCount.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next();
      deleteMinByKey(keyToDelete);
    }

    // Key is new
    currentMin = 1;
    putCountAndUpdateLRU(key, currentMin);
    valueByKey.put(key, value);
  }

  private void deleteMinByKey(int key) {
    valueByKey.remove(key);
    countByKey.remove(key);
    lruByCount.get(currentMin).remove(key);
  }
}
