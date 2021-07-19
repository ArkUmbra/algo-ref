package com.arkumbra.algo.bits;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BitwiseTricksTest {

  private BitwiseTricks bitwiseTricks;

  @Before
  public void setUp() {
    this.bitwiseTricks = new BitwiseTricks();
  }

  @Test
  public void testHammingWeight() {
    int input = 0x101100;
    int expected = 3;

    assertEquals(expected, bitwiseTricks.hammingWeight(input));
    assertEquals(expected, bitwiseTricks.hammingWeight2(input));
  }
}
