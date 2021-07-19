package com.arkumbra.algo.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class ThreadTest {

  @Test
  public void testExecutorService() throws InterruptedException {
    Runnable runnable = new Runnable() {
      int myNumber = 0;
      @Override
      public void run() {
        for (int i = 0; i < 1000000; i++) {
          myNumber++;
        }
        System.out.println(Thread.currentThread().getName() + " " + myNumber);
      }
    };

    ExecutorService execService = Executors.newFixedThreadPool(3);
    execService.submit(runnable);
    execService.submit(runnable);
    execService.submit(runnable);

    execService.shutdown();

    execService.awaitTermination(20L, TimeUnit.SECONDS);
  }

  @Test
  public void testLockAndNotifyEtc(){
    // TODO
  }

}
