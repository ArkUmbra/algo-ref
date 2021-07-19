package com.arkumbra.algo.sort;

public class MinHeapSort implements InPlaceSort {

  @Override
  public void sort(int[] arr) {
    int heapSize = arr.length - 1;
    buildMinHeap(arr, heapSize);
    for (int i = heapSize; i > 0; i--) {
      swap(arr, i, 0);
      heapSize--;
      minHeapify(arr, 0, heapSize);
    }
  }

  private void buildMinHeap(int[] arr, int heapSize) {
    for (int i = heapSize / 2; i >= 0; i--) {
      minHeapify(arr, i, heapSize);
    }
  }

  private void minHeapify(int[] arr, int i, int heapSize) {
    int left = left(i);
    int right = right(i);

    int smallest = i;

    if (left <= heapSize && arr[left] < arr[i])
      smallest = left;
    if (right <= heapSize && arr[right] < arr[smallest])
      smallest = right;

    if (smallest != i) {
      swap(arr, i, smallest);
      minHeapify(arr, smallest, heapSize);
    }
  }

  private void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }

  private int left(int i) {
    return i * 2 + 1;
  }

  private int right(int i) {
    return i * 2 + 2;
  }
}
