package com.arkumbra.algo.arrays;

public class InsertionSort {

  public int[] sort(int[] input) {

    for (int j = 1; j < input.length; j++) {
      int cur = input[j];
      int i = j - 1;

      while (i >= 0 && input[i] > cur) {
        input[i + 1] = input[i];
        i--;
      }

      input[i + 1] = cur;
    }

    // sorts in place
    return input;
  }

}
