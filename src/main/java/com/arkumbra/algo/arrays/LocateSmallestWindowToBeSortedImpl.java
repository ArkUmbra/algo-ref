package com.arkumbra.algo.arrays;

public class LocateSmallestWindowToBeSortedImpl implements LocateSmallestWindowToBeSorted {

  @Override
  public Tuple<Integer, Integer> findIndexRange(int[] elements) {

    int right = 0;
    // find latest index of a number which is smallest than current max
    int currentMax = Integer.MIN_VALUE;
    for (int i = 0; i < elements.length; i++) {
      int num = elements[i];
      currentMax = Math.max(currentMax, num);
      if (currentMax > num) right = i;
    }

    int left = 0;
    // find latest index of a number which is smallest than current max
    int currentMin = Integer.MAX_VALUE;
    for (int i = elements.length - 1; i >= 0; i--) {
      int num = elements[i];
      currentMin = Math.min(currentMin, num);
      if (currentMin < num) left = i;
    }

    // go in reverse - find earliest index of a number that is larger than current max
    return new Tuple<>(left, right);
  }
}
