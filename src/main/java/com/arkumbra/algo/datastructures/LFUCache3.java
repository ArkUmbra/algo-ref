package com.arkumbra.algo.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;

public class LFUCache3 implements Cache<Integer> {

  private int currentMin = -1;
  private final int capacity;
  
  private final Map<Integer, Integer> valueByKey = new HashMap<>();
  private final Map<Integer, Integer> countByKey = new HashMap<>();
  private final Map<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();
  
  public LFUCache3(int capacity) {
    this.capacity = capacity;
  }
  
  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;
    
    int useCount = countByKey.get(key);
    // remove from current LRU - we'll add it again in a moment
    lruByCount.get(useCount).remove(key);
    
    if (useCount == currentMin && lruByCount.get(currentMin).isEmpty()) currentMin++;
    
    putCountAndAddToLRU(key, useCount + 1);
    return valueByKey.get(key);
  }

  private void putCountAndAddToLRU(int key, int count) {
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
      get(key); // Update count for key
      return;
    }

    // If max capacity
    if (valueByKey.size() >= capacity) {
      int keyToDelete = lruByCount.get(currentMin).iterator().next(); // Get least frequent of least used
      deleteMinimumByKey(keyToDelete);
    }

    // key does not exist
    currentMin = 1;
    putCountAndAddToLRU(key, currentMin);
    valueByKey.put(key, value);
  }

  private void deleteMinimumByKey(int keyToDelete) {
    lruByCount.get(currentMin).remove(keyToDelete);
    valueByKey.remove(keyToDelete);
    countByKey.remove(keyToDelete);
  }
}
