package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearchLeftmost3 {

  public int getIndex(List<Integer> sortedItems, int target) {
    int left = 0;
    int right = sortedItems.size() - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      int found = sortedItems.get(mid);

      if (found < target) left = mid + 1;
      else right = mid;
    }

    return left;
  }
}
