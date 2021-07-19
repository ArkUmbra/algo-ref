package com.arkumbra.algo.arrays;

import com.arkumbra.algo.Printer;

public class ProductOfAllOtherElementsImpl implements ProductOfAllOtherElements {

  @Override
  public int[] getProduct(int[] input) {

    // prefix sum
    int[] prefixSums = new int[input.length];
    for (int i = 0; i < prefixSums.length; i++) {
      int num = input[i];
      if (i == 0) {
        prefixSums[i] = num; // If first number, just insert directly
      } else {
        prefixSums[i] = prefixSums[i - 1] * num; // Otherwise, mutiply by the previous num
      }
    }

    Printer.print(prefixSums);

    // suffix sum
    int[] suffixSums = new int[input.length];
    int start = input.length - 1;
    for (int i = start; i >= 0 ; i--) { // Scan from the back
      int num = input[i]; // but also insert in reverse
      if (i == start) {
        suffixSums[i] = num; // If first number, just insert directly
      } else {
        suffixSums[i] = suffixSums[i + 1] * num; // Otherwise, mutiply by the previous num
      }
    }

    Printer.print(suffixSums);

    // answer array

    // suffix sum
    int[] answer = new int[input.length];
    for (int i = 0; i < prefixSums.length; i++) {

      int pre = (i == 0) ? 1 : prefixSums[i - 1];
      int suf = (i == prefixSums.length - 1) ? 1 : suffixSums[i + 1];

      answer[i] = pre * suf;
    }

    Printer.print(answer);


    return answer;
  }
}
