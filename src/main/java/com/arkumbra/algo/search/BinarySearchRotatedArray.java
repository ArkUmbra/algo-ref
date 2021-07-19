package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearchRotatedArray {

  public int getIndex(List<Integer> sortedItems, int toFind) {
    int left = 0;
    int right = sortedItems.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int found = sortedItems.get(mid);

      if (found == toFind) return mid;
      // If left of array is sorted
      if (sortedItems.get(left) <= found) {
        // If target is on left side
        if (toFind >= sortedItems.get(left) && toFind < sortedItems.get(mid)) right = mid - 1;
        else left = mid + 1;

      // Right side is sorted
      } else {
        // if target is on right side
        if (toFind > sortedItems.get(mid) && toFind <= sortedItems.get(right)) left = mid + 1;
        right = mid - 1;
      }
    }

    return -1;
  }

}
