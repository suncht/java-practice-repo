package com.sample.concurrent;


import org.junit.Test;

public class MyBlockingQueueTest {

    @Test
    public void test1() {
        final MyBlockingQueue queue = new MyBlockingQueue(3);

        for (int i = 0; i < 20; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.add(index + "==>" + Math.random());
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("==>" + queue.take());
                    }
                }
            }).start();
        }
    }
}
