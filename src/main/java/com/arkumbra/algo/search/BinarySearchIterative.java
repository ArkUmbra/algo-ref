package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearchIterative {

  public int getIndex(List<Integer> sortedItems, int toFind) {
    int left = 0;
    int right = sortedItems.size() - 1;

    while (left <= right) {
      int mid = left + (right - left)/2;
      int found = sortedItems.get(mid);

      if (found == toFind) return mid;
      if (found > toFind) right = mid - 1;
      else left = mid + 1;
    }

    return -1;
  }

}
