package com.arkumbra.algo.arrays;

import java.util.Objects;

public interface LocateSmallestWindowToBeSorted {

  Tuple<Integer, Integer> findIndexRange(int[] elements);

  class Tuple<L, R> {
    private final L l;
    private final R r;

    public Tuple(L l, R r) {
      this.l = l;
      this.r = r;
    }

    public L getL() {
      return l;
    }

    public R getR() {
      return r;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Tuple<?, ?> tuple = (Tuple<?, ?>) o;
      return Objects.equals(l, tuple.l) &&
          Objects.equals(r, tuple.r);
    }

    @Override
    public String toString() {
      return "Tuple{" +
          "l=" + l +
          ", r=" + r +
          '}';
    }
  }

}
