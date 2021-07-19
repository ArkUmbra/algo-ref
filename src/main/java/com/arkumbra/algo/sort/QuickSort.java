package com.arkumbra.algo.sort;

public class QuickSort {

  public void sort(int[] input) {
    quicksort(input, 0, input.length - 1);
  }

  private void quicksort(int[] arr, int start, int end) {
    if (start > end) return;

    int partitionPoint = partition(arr, start, end);
    quicksort(arr, start, partitionPoint - 1);
    quicksort(arr, partitionPoint + 1, end);
  }

  private int partition(int[] arr, int start, int end) {
    int pivot = arr[end];
    int i = start - 1;

    for (int j = start; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;
        swap(arr, i, j);
      }
    }

    swap(arr, i + 1, end);
    return i + 1;
  }

  private void swap(int[] arr, int a, int b) {
    int tmp = arr[a];
    arr[a] = arr[b];
    arr[b] = tmp;
  }

}
