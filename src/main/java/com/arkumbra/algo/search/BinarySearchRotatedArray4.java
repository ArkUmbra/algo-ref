package com.arkumbra.algo.search;

import java.util.List;

public class BinarySearchRotatedArray4 {


  public int getIndex(List<Integer> sortedItems, int toFind) {
    int left = 0;
    int right = sortedItems.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int found = sortedItems.get(mid);

      if (found == toFind) return mid;

      // Check if left side is sorted
      if (sortedItems.get(left) <= found) {
        // If our target is on the left side
        if (toFind >= sortedItems.get(left) && toFind < found) right = mid - 1;
        else left = mid + 1;

      } else {
        // If our target is on the right
        if (toFind > found && toFind <= sortedItems.get(right)) left = mid + 1;
        else right = mid - 1;
      }
    }

    return -1;
  }

}
