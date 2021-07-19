package com.arkumbra.algo.datastructures;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LeastFrequentlyUsedCache implements Cache<Integer> {

  private int currentMin = -1;

  private final int capacity;
  private final HashMap<Integer, Integer> valueByKey = new HashMap<>();
  private final HashMap<Integer, Integer> countByKey = new HashMap<>();
  private final HashMap<Integer, LinkedHashSet<Integer>> lruByCount = new HashMap<>();

  public LeastFrequentlyUsedCache(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Integer get(int key) {
    if (! valueByKey.containsKey(key)) return -1;

    int itemCount = countByKey.get(key);
    LinkedHashSet<Integer> itemsWithThisCount = lruByCount.get(itemCount);
    itemsWithThisCount.remove(key);

    // Nothing in the current min count, so increment min
    if (itemCount == currentMin && lruByCount.get(itemCount).isEmpty()) currentMin++;

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
    if (valueByKey.containsKey(key)) {
      valueByKey.put(key, value);
      get(key); // update key count
      return;
    }

    // Need to make space
    if (valueByKey.size() >= capacity) {
      // least recently used, out of least frequently used block
      int keyToDelete = lruByCount.get(currentMin).iterator().next();
      removeByKey(keyToDelete);
    }

    // If we got here, this key is new
    currentMin = 1;
    putCount(key, currentMin);
    valueByKey.put(key, value);
  }

  private void removeByKey(int key) {
    lruByCount.get(currentMin).remove(key);
    valueByKey.remove(key);
    countByKey.remove(key);
  }
}
