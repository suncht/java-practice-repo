package com.sample.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用CountDownLatch实现多个任务并发计算，并汇总结果
 * @author changtan.sun
 *
 */
public class MyComputeTask {
    static AtomicLong sums = new AtomicLong();

    public MyComputeTask() {
    }

    public long compute(int number, int part) {
        number = number + 1;
        int parts = (number + part - 1) / part;
        CountDownLatch latch = new CountDownLatch(parts + 1);

        for (int i = 0; i < parts; i++) {
            long min = i * part;
            long max = (i + 1) * part < number ? (i + 1) * part : number;

            new Thread(new MyTask(min, max, latch)).start();
        }
        latch.countDown();
        try{
            latch.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


        return sums.get();
    }

    private static class MyTask implements Runnable {

        private long min;
        private long max;

        private CountDownLatch latch;

        public MyTask(long min, long max, CountDownLatch latch) {
            this.min = min;
            this.max = max;
            this.latch = latch;
        }

        @Override
        public void run() {
            long sum = 0;
            for (long i = min; i < max; i++) {
                sum += i;
            }
            sums.getAndAdd(sum);
            latch.countDown();
        }

    }
}