package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearch2 {

  public int getIndex(List<Integer> sortedItems, int toFind) {
    return search(sortedItems, toFind, 0, sortedItems.size() - 1);
  }

  private int search(List<Integer> sortedItems, int toFind, int low, int high) {
    if (low > high) return -1;

    int mid = low + ((high - low) / 2);
    int found = sortedItems.get(mid);
    if (found == toFind) return mid;

    if (found < toFind)
      return search(sortedItems, toFind, mid + 1, high);
    return search(sortedItems, toFind, low, mid);
  }

}
