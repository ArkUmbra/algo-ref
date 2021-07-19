package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearchIterative3 {

  public int getIndex(List<Integer> sortedItems, int target) {
    int left = 0;
    int right = sortedItems.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int found = sortedItems.get(mid);
      if (found == target) return mid;
      if (found > target) right = mid - 1;
      else left = mid + 1;
    }

    return -1;
  }

}
