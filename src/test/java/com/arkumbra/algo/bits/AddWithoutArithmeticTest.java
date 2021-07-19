package com.arkumbra.algo.bits;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddWithoutArithmeticTest {

  private AddWithoutArithmetic sut = new AddWithoutArithmetic();

  @Test
  public void testAddPositive() {
    int a = 23;
    int b = 52;
    int answer = a  + b;

    assertEquals(answer, sut.add(a, b));
  }

}
