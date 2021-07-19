package com.arkumbra.algo.bits;

public class BitReverserImpl implements BitReverser {

  @Override
  public int reverseBitsOfInteger(int input) {
    System.out.println(Integer.toBinaryString(input));
    System.out.println(Integer.toHexString(input));
    int reversed = 0;

    for (int i = 0; i < Integer.BYTES * 8; i++) {
      int tmp = ((input >> i) & input) << (32 - i - 1);
//      System.out.println(Integer.toBinaryString(tmp));
      reversed |= tmp;
//      System.out.println(Integer.toBinaryString(reversed));
    }

    System.out.println(Integer.toBinaryString(reversed));
    System.out.println(Integer.toHexString(reversed));
    return 0;
  }
}
