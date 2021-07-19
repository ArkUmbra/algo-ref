package com.arkumbra.algo.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

public class CacheTest {

  @Test
  public void testLeastRecentlyUsedCache() {
    List<Cache<Integer>> implementations = List.of(
        new LRUCache(2),
        new LRUCacheDirectlyFromLeetcode(2),
        new LRUCache2(2),
        new LRUCache3(2),
        new LRUCache4(2),
        new LRUCache5(2),
        new LRUCache6(2)
    );

    for (Cache<Integer> implementation : implementations) {
      implementation.put(1,1);
      implementation.put(2, 2);
      assertEquals(Integer.valueOf(1), implementation.get(1));
      implementation.put(3, 3);
      assertEquals(Integer.valueOf(-1), implementation.get(2));
      implementation.put(4, 4);
      assertEquals(Integer.valueOf(-1), implementation.get(1));
      assertEquals(Integer.valueOf(3), implementation.get(3));
      assertEquals(Integer.valueOf(4), implementation.get(4));
    }
  }

  @Test
  public void testLeastFrequentlyUsedCache() {
    List<Cache<Integer>> implementations = List.of(
        new LeastFrequentlyUsedCache(2),
        new LFUCache2(2),
        new LFUCache3(2),
        new LFUCache4(2),
        new LFUCache5(2),
        new LFUCache6(2),
        new LFUCache7(2),
        new LFUCache8(2)
    );

    for (Cache<Integer> implementation : implementations) {
      implementation.put(1,1);
      implementation.put(2, 2);
      assertEquals(Integer.valueOf(1), implementation.get(1));
      implementation.put(3, 3);
      assertEquals(Integer.valueOf(-1), implementation.get(2));
      assertEquals(Integer.valueOf(3), implementation.get(3));
      implementation.put(4, 4);
      assertEquals(Integer.valueOf(-1), implementation.get(1));
      assertEquals(Integer.valueOf(3), implementation.get(3));
      assertEquals(Integer.valueOf(4), implementation.get(4));
    }
  }

}
