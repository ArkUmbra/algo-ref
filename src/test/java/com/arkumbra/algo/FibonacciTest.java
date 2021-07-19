package com.arkumbra.algo;

import static org.junit.Assert.assertEquals;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Random;

import org.junit.Test;

public class FibonacciTest {

  @Test
  public void test() {

    assertEquals(0, fib(0));
    assertEquals(1, fib(1));
    assertEquals(1, fib(2));
    assertEquals(2, fib(3));
    assertEquals(3, fib(4));
    assertEquals(144, fib(12));
  }
  private int fib(int index) {
    int a = 0;
    int b = 1;
    if (index == 0) return a;

    for (int i = 2; i <= index; i++) {
      int ans = a + b;
      a = b;
      b = ans;
    }

    return b;
  }

}
