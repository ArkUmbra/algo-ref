package com.arkumbra.algo.arrays;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ProductOfAllOtherElementsTest {

  private ProductOfAllOtherElements productOfAllOtherElements = new ProductOfAllOtherElementsImpl();

  @Test
  public void testExample1() {
    int[] input = new int[]{1, 2, 3, 4, 5};
    int[] expectedOutput = new int[]{120, 60, 40, 30, 24};

    int[] actual = productOfAllOtherElements.getProduct(input);

    assertArrayEquals(expectedOutput, actual);
  }

  @Test
  public void testExample2() {
    int[] input = new int[]{3, 2, 1};
    int[] expectedOutput = new int[]{2, 3, 6};

    int[] actual = productOfAllOtherElements.getProduct(input);

    assertArrayEquals(expectedOutput, actual);
  }

}
