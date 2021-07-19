package com.arkumbra.algo;

public class Printer {

  public static void print(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int num : arr) {
      sb.append(num);
      sb.append(",");
    }
    System.out.println(sb.toString());
  }

}
