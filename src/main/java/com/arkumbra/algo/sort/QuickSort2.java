package com.arkumbra.algo.sort;

public class QuickSort2 {

  public void sort(int[] input) {
    quicksort(input, 0, input.length - 1);
  }

  private void quicksort(int[] arr, int start, int end) {
    if (start > end) return;

    int partition = partition(arr, start, end);
    quicksort(arr, start, partition - 1);
    quicksort(arr, partition + 1, end);
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

    swap(arr, end, i + 1);
    return i + 1;
  }


  private void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }
}
