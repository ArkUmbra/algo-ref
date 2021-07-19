package com.arkumbra.algo.sort;

public class MergeSort implements InPlaceSort {

  @Override
  public void sort(int[] arr) {
    mergesort(arr, 0, arr.length -1);
  }

  private void mergesort(int[] arr, int start, int end) {
    if (start >= end) return;

    int mid = (start + end ) / 2;
    mergesort(arr, start, mid);
    mergesort(arr, mid +1, end);

    merge(arr, start, mid, end);
  }

  private void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] tempLeft = new int[n1];
    System.arraycopy(arr, left, tempLeft, 0, n1);
    int[] tempRight = new int[n2];
    System.arraycopy(arr, mid + 1, tempRight, 0, n2);

    int i = 0;
    int j = 0;
    int k = left;
    while (i < n1 && j < n2) {
      if (tempLeft[i] <= tempRight[j]) {
        arr[k] = tempLeft[i];
        i++;
      } else {
        arr[k] = tempRight[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      arr[k] = tempLeft[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = tempRight[j];
      j++;
      k++;
    }
  }

}
