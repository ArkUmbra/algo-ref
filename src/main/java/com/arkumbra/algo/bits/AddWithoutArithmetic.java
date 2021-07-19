package com.arkumbra.algo.bits;

public class AddWithoutArithmetic {

  public int add(int a, int b) {
    if (a < 0 || b < 0) {
      throw new RuntimeException("Only works with positive nums");
    }

    while (b != 0) {
      int carry = (a & b);
      a = a ^ b;
      b = carry << 1;
    }

    return a;
  }

}
