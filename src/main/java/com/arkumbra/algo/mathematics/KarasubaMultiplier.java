package com.arkumbra.algo.mathematics;

import java.math.BigInteger;

public class KarasubaMultiplier {
  private static final BigInteger BASE_CASE = new BigInteger("10");



  public BigInteger multiplyTogether(BigInteger x, BigInteger y) {
    if (x.compareTo(BASE_CASE) < 0 && y.compareTo(BASE_CASE) < 0) {
      return new BigInteger(String.valueOf(x.intValue() * y.intValue()));
    }

    double n = Math.max(x.toString().length(), y.toString().length());
    int m = (int)(n / 2);

    BigInteger power = BigInteger.TEN.pow(m);

    BigInteger xHigh = x.divide(power);
    BigInteger xLow = x.mod(power);

    BigInteger yHigh = y.divide(power);
    BigInteger yLow = y.mod(power);

    BigInteger a = multiplyTogether(xHigh, yHigh);
    BigInteger d = multiplyTogether(xLow, yLow);
    BigInteger e = multiplyTogether(xHigh.add(xLow), yHigh.add(yLow))
        .add(a.negate())
        .add(d.negate());

    BigInteger powerSq = new BigInteger(String.valueOf(10)).pow(m*2);
    return a.multiply(powerSq).add(e.multiply(power)).add(d);


    // =======

//    BigInteger z0 = multiplyTogether(xLow, yLow);
//    BigInteger z1 = multiplyTogether(xHigh.add(xLow), yHigh.add(yLow));
//    BigInteger z2 = multiplyTogether(xHigh, yHigh);
//
//    BigInteger answerPart1 = z2.multiply(
//          BigInteger.TEN.pow((int) (m*2))
//        );
//    BigInteger answerPart2 = (z1
//          .add(z2.negate().add(z0.negate()))
//    ).multiply(power);
//
//    return answerPart1.add(answerPart2).add(z0);

    // =======

//    BigInteger doublePower = BigInteger.TEN.pow((int) (m*2));
//
//    BigInteger a = x.divide(power);
//    BigInteger b = x.mod(power);
//    BigInteger c = y.divide(power);
//    BigInteger d = y.mod(power);
//
//    BigInteger ac = multiplyTogether(a, c);
//    BigInteger bd = multiplyTogether(b, d);
//    BigInteger adPlusBc = multiplyTogether(a.add(b), c.add(d))
//        .add(ac.negate())
//        .add(bd.negate());
//    return ac.multiply(doublePower)
//        .add(
//            adPlusBc.multiply(power)
//        ).add(bd);
  }


}
