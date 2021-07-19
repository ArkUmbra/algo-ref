package com.arkumbra.algo.tree;

public interface Tree<T> {

  void insert(T value);
  boolean contains(T value);
  T remove(T value);

}
