package com.arkumbra.algo.bits;

import org.junit.Test;

public class BitReverserTest {

  private BitReverser sut = new BitReverserImpl();

  @Test
  public void testReverseBitsOfInteger() {
    int input = 123;
    sut.reverseBitsOfInteger(input);
  }

}
