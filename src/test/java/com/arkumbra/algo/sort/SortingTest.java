package com.arkumbra.algo.sort;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import org.junit.Test;

public class SortingTest {

  @Test
  public void testQuickSort() {
    int[] input = new int[]{5,2,4,6,1,3};
    int[] expected = new int[]{1,2,3,4,5,6};

    new QuickSort().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);

    //

    input = new int[]{5,2,4,6,1,3};
    expected = new int[]{1,2,3,4,5,6};

    new QuickSort2().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);
    //

    input = new int[]{5,2,4,6,1,3};
    expected = new int[]{1,2,3,4,5,6};

    new QuickSort3().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);
  }

  @Test
  public void testMaxHeapSort() {
    int[] input = new int[]{5,2,4,6,1,3,7,9,8};
    int[] expected = new int[]{1,2,3,4,5,6,7,8,9};

    new MaxHeapSort().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);
  }

  @Test
  public void testMinHeapSort() {
    int[] input = new int[]{5,2,4,6,1,3,7,9,8};
    int[] expected = new int[]{9,8,7,6,5,4,3,2,1};

    new MinHeapSort().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);
  }

  @Test
  public void testMergeSort() {
    int[] input = new int[]{5,2,4,6,1,3,7,9,8};
    int[] expected = new int[]{1,2,3,4,5,6,7,8,9};

    new MergeSort().sort(input);

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(expected));
    assertArrayEquals(input, expected);
  }

}
