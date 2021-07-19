package com.arkumbra.algo.bits;

public class BitwiseTricks {

  /**
   * @param number integer value to check weight of
   * @return count of '1' values in the input number's binary representation
   */
  public int hammingWeight(int number) {
    int count = 0;

    // we don't actually do anything with i
    // It is just used to count 32 bits
    for (int i = 0; i < 32; i++) {
      int masked = number & 1;
      count += masked;
      System.out.println(String.format("Input %s, masked %s, count %s", Integer.toBinaryString(number), masked, count));
      number = number >> 1;
    }
    return count;
  }

  /**
   * @param number integer value to check weight of
   * @return count of '1' values in the input number's binary representation
   */
  public int hammingWeight2(int number) {
    int count = 0;

    // Faster than the above algorithm and a bit cleaner
    // The nice trick is that doing bitwise AND with a number and number - 1 will always remove its
    // least significant bit. So this will count
    // e.g.
    // n          = 110100
    // n - 1      = 110011
    // n&(n - 1)  = 110000

    while (number != 0) {
      count++;
      number &= (number - 1);
    }
    return count;
  }

}
