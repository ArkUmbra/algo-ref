package com.arkumbra.algo.arrays;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;

public class InsertionSortTest {

  private InsertionSort insertionSort = new InsertionSort();

  @Test
  public void testSort() {
    int[] input = new int[]{5,2,4,6,1,3};
    int[] expected = new int[]{1,2,3,4,5,6};

    int[] answer = insertionSort.sort(input);

    System.out.println(Arrays.toString(expected));
    System.out.println(Arrays.toString(answer));
  }

  @Test
  public void testSortRandom() {
    Random r = new Random();
    int[] input = new int[]{r.nextInt(100), r.nextInt(100),r.nextInt(100),
        r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),
        r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),
        r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100)};

    int[] answer = insertionSort.sort(input);

    System.out.println(Arrays.toString(answer));
  }

}
