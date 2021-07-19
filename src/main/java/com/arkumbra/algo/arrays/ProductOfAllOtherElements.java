package com.arkumbra.algo.arrays;


/**
 * Given an array of integers, return a new array such that each element at index i of the new array
 * is the product  of all the numbers in the original array except the one at i.
 *
 * Follow up: what if you can't use division?
 */
public interface ProductOfAllOtherElements {

  int[] getProduct(int[] input);

}