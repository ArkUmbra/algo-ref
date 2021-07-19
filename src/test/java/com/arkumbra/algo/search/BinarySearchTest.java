package com.arkumbra.algo.search;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void test() {
    int toFind = 78;
    List<Integer> items = List.of(1,2,3,4,5,17,18,19,20,21,22,34,77,toFind, 82,84,92,93);

    int expected = 13;
    assertBinarySearch(items, toFind, expected);
  }

  @Test
  public void testRandom() {
    Random r = new Random();
    int toFind = r.nextInt();
    List<Integer> items = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      items.add(r.nextInt(999999));
    }
    items.add(toFind);
    Collections.sort(items);

    int expected = items.indexOf(toFind);
    assertBinarySearch(items, toFind, expected);
  }

  @Test
  public void testWhenLast() {
    int toFind = 95;
    List<Integer> items = List.of(1,2,3,4,5,17,18,19,20,21,22,34,77, 82,84,92,93, toFind);
    int expected = items.size() - 1;
    assertBinarySearch(items, toFind, expected);
  }

  private void assertBinarySearch(List<Integer> sortedItems, int toFind, int expected) {
    int index = new BinarySearch().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearch2().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchIterative().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchIterative2().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchIterative3().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchIterative4().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
  }



  @Test
  public void testFindLeftMost() {
    int toFind = 2;
    List<Integer> items = List.of(1,1,1,1,1,1,2,2,2,3,4,5,17,18,19,20,21,22,34,77,82,84,92,93);
    int expected = 6;
    assertBinarySearchLeftmost(items, toFind, expected);
  }

  private void assertBinarySearchLeftmost(List<Integer> sortedItems, int toFind, int expected) {
    int index = new BinarySearchLeftmost().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchLeftmost2().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchLeftmost3().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchLeftmost4().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
  }

  @Test
  public void testFindRightMost() {
    int toFind = 2;
    List<Integer> items = List.of(1,1,1,1,1,2,2,2,3,4,5,17,18,19,20,21,22,34,77, 82,84,92,93, toFind);
    int expected = 7;
    assertBinarySearchRightmost(items, toFind, expected);
  }

  private void assertBinarySearchRightmost(List<Integer> sortedItems, int toFind, int expected) {
    int index = new BinarySearchRightmost().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
    index = new BinarySearchRightmost2().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
    index = new BinarySearchRightmost3().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
    index = new BinarySearchRightmost4().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
  }

  @Test
  public void testFindInRotatedArray() {
    int toFind = 0;
    List<Integer> items = List.of(4,5,6,7,toFind,1,2);
    int expected = 4;
    assertBinarySearchRotated(items, toFind, expected);
  }

  private void assertBinarySearchRotated(List<Integer> sortedItems, int toFind, int expected) {
    int index = new BinarySearchRotatedArray().getIndex(sortedItems, toFind);
    assertEquals(expected, index);

    index = new BinarySearchRotatedArray4().getIndex(sortedItems, toFind);
    assertEquals(expected, index);
  }

}
