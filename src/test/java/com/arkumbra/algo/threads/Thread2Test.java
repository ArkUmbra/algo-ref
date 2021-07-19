package com.arkumbra.algo.threads;

import org.junit.Test;

public class Thread2Test {

  @Test
  public void testJoin() {
    Runnable myRunnable = new Runnable() {
      public int counter = 0;
      @Override
      public void run(){
        for (int i = 0; i < 10000; i++) {
          counter++;
        }
        System.out.println(counter);
      }
    };

    Thread t1 = new Thread(myRunnable);
    Thread t2 = new Thread(myRunnable);
    Thread t3 = new Thread(myRunnable);
    t1.start();
    t2.start();
    t3.start();
    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testCauseDeadlock() {
    Thread a = new Thread(() -> lockOnStringThenInteger());
    Thread b = new Thread(() -> lockOnIntegerThenString());

    a.start();
    b.start();

    try {
      a.join();
      b.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void lockOnStringThenInteger() {
    synchronized(String.class) {
      System.out.println("a- Locked String");
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (Integer.class) {
        System.out.println("a- Locked Integer");
      }
    }
  }

  private void lockOnIntegerThenString() {
    synchronized(Integer.class) {
      System.out.println("b - Locked Integer");
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (String.class) {
        System.out.println("b - Locked String");
      }
    }
  }

  @Test
  public void testWaitNotify() {
    Thread a = new Thread(() -> lockThenWait());
    Thread b = new Thread(() -> doThenNotify());

    a.start();
    b.start();

    try {
      a.join();
      b.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }


  private void lockThenWait() {
    synchronized (String.class) {
      System.out.println("a - entered");
      try {
        String.class.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        return;
      }

      System.out.println("a - got the lock back");
    }
  }


  private void doThenNotify() {
    System.out.println("b - waiting for string lock");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return;
    }

    synchronized (String.class) {
      System.out.println("b - entered");
      String.class.notifyAll();
    }

    System.out.println("b - will notify");
  }

}
