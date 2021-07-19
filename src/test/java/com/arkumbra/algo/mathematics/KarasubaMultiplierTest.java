package com.arkumbra.algo.mathematics;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.Test;

public class KarasubaMultiplierTest {

  private KarasubaMultiplier karasubaMultiplier = new KarasubaMultiplier();

  @Test
  public void testSimpleNumbers() {
    BigInteger a = new BigInteger("213");
    BigInteger b = new BigInteger("100");
    BigInteger expected = new BigInteger("21300");

    assertEquals(expected, karasubaMultiplier.multiplyTogether(a, b));
  }

  @Test
  public void testExample1() {
    BigInteger a = new BigInteger("5678");
    BigInteger b = new BigInteger("1234");
    BigInteger expected = new BigInteger("7006652");

    assertEquals(expected, karasubaMultiplier.multiplyTogether(a, b));
  }

  @Test
  public void testLarge() {
    BigInteger a = new BigInteger("12341233");
    BigInteger b = new BigInteger("585710278");
    BigInteger expected = new BigInteger("7228387011292774");

    assertEquals(expected, karasubaMultiplier.multiplyTogether(a, b));
  }

  @Test
  public void testMassive() {
    BigInteger a = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
    BigInteger b = new BigInteger("31102");
    BigInteger expected = a.multiply(b);
    System.out.println(expected);

    assertEquals(expected, karasubaMultiplier.multiplyTogether(a, b));
  }

  @Test
  public void testTwo64Bit() {
    BigInteger a = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
    BigInteger b = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
    BigInteger expected = a.multiply(b);
    System.out.println(expected);

    assertEquals(expected, karasubaMultiplier.multiplyTogether(a, b));
  }



}
