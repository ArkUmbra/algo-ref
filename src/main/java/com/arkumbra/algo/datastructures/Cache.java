package com.arkumbra.algo.datastructures;

public interface Cache<T> {

  T get(int key);

  void put(int key, T value);

}
