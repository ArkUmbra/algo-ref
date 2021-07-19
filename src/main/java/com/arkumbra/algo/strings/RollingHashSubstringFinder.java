package com.arkumbra.algo.strings;

import java.util.ArrayDeque;
import java.util.Queue;

// TODO TODO
public class RollingHashSubstringFinder implements FindSubstringInString {

  @Override
  public int findFirstIndex(String source, String subString) {
    RollingHash subStrHash = new RollingHash(subString);
    RollingHash sourceHash = null;

    for (int i = 0; i < source.length(); i++) {
      if (i == 0) {
//        sourceHash = new RollingHash(source, subString.length());
      } else {
        sourceHash.rollNext(source.charAt(i));
      }
      
      if (subStrHash.equals(sourceHash)) {
        // If hash matches (sometimes false-positive), then do a real string compare to check
        if (directCompare(source, subString, i)) return i;
      }
    }
    
    return -1;
  }
  
  private boolean directCompare(String source, String expected, int fromIndex) {
    for (int i = 0; i < expected.length(); i++) {
      if (source.charAt(i + fromIndex) != expected.charAt(i)) {
        return false;
      }
    }
    return true;
  }
}

class RollingHash {
  private static final int PRIME = 7001;
  private final int BASE = 31;
  private Queue<Character> window = new ArrayDeque<>();
  private int currentHash;

  public RollingHash(String subString) {
//    this.base = calcBase(subString.length()); // No? What is base? Just a random prime?

    for (char c : subString.toCharArray()) {
      window.add(c);
    }
  }

//  public RollingHash(String source, int length) {
//    this.base = calcBase(length);
//  }

  private int calcBase(int inputLength) {
    return (inputLength - 1) % PRIME;
  }
  
  private int createHash(char[] input) {
    int hash = 0;

    for (int i = 0; i < input.length; i++) {
      int pow = input.length - 1 - i;
      int val = (int)(input[i] * Math.pow(BASE, pow));
      hash += val;
    }

    return hash;
  }

  public void rollNext(char nextChar) {
    // remove first char
    Character toRemove = window.remove();
    slideHash(toRemove, nextChar);
    window.add(nextChar);
  }

  private int slideHash(int out, int in) {
    return -1;
//    re
  }
}
