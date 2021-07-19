package com.arkumbra.algo.sort;

public class MaxHeapSort implements InPlaceSort {

  @Override
  public void sort(int[] arr) {
    int heapSize = arr.length - 1;
    buildMaxHeap(arr, heapSize);
    for (int i = heapSize; i > 0; i--) {
      swap(arr, 0, i);
      heapSize--;
      maxHeapify(arr, 0, heapSize);
    }
  }

  private void buildMaxHeap(int[] arr, int heapSize) {
    for (int i = heapSize / 2; i >= 0; i--) {
      maxHeapify(arr, i, heapSize);
    }
  }

  private void maxHeapify(int[] arr, int i, int heapSize) {
    int left = left(i);
    int right = right(i);

    // Find if this, left or right is the largest
    int largest = i;

    if (left <= heapSize && arr[left] > arr[i]) {
      largest = left;
    }
    if (right <= heapSize && arr[right] > arr[largest]) {
      largest = right;
    }

    if (largest != i) {
      swap(arr, i, largest);
      maxHeapify(arr, largest, heapSize);
    }
  }

  private void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }

  private int parent(int i) {
    return i / 2;
  }

  private int left(int i) {
    return i * 2 + 1;
  }

  private int right(int i) {
    return i * 2 + 2;
  }

}
