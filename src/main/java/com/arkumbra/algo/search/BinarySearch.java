package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearch {

  public int getIndex(List<Integer> sortedItems, int toFind) {
    return getIndex(sortedItems, toFind, 0, sortedItems.size() - 1);
  }

  private int getIndex(List<Integer> sortedItems, int toFind, int low, int high) {
    if (low > high) return -1;

    int mid = low + ((high - low) / 2);

    int found = sortedItems.get(mid);
    if (found == toFind) return mid;
    if (found < toFind)
      return getIndex(sortedItems, toFind, mid+1, high);
    return getIndex(sortedItems, toFind, low, mid);
  }

}
