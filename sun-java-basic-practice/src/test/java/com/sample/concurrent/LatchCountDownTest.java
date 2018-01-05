package com.sample.concurrent;

import org.junit.Test;

public class LatchCountDownTest {
    @Test
    public void test1() {
        MyComputeTask t = new MyComputeTask();
        long sum = t.compute(1000000, 100);
        System.out.println(sum);
    }
}
