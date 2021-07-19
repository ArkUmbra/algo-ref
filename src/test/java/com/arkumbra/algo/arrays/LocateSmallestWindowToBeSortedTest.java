package com.arkumbra.algo.arrays;

import static org.junit.Assert.assertEquals;

import com.arkumbra.algo.arrays.LocateSmallestWindowToBeSorted.Tuple;
import org.junit.Test;

public class LocateSmallestWindowToBeSortedTest {

  private LocateSmallestWindowToBeSorted sut = new LocateSmallestWindowToBeSortedImpl();

  @Test
  public void testExample1() {
    int[] input = new int[]{3, 7, 5, 6, 9};
    Tuple<Integer, Integer> expectedRange = new Tuple<>(1, 3);

    Tuple<Integer, Integer> range = sut.findIndexRange(input);
    System.out.println(range.toString());
    assertEquals(expectedRange, range);
  }

  @Test
  public void testManuallyAdded1() {
    int[] input = new int[]{9, 19, 67, 66, 68, 69, 70, 71, 66};
    Tuple<Integer, Integer> expectedRange = new Tuple<>(2, 8);

    Tuple<Integer, Integer> range = sut.findIndexRange(input);
    System.out.println(range.toString());
    assertEquals(expectedRange, range);
  }

}
