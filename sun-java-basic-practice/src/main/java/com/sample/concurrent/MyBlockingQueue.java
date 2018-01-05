package com.sample.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReetrantLock实现自定义阻塞队列
 */
public class MyBlockingQueue {
    private final Lock lock = new ReentrantLock();
    private Condition addCondition = lock.newCondition();
    private Condition takeCondition = lock.newCondition();

    private Object[] data;
    private int count = 0;
    private int position = -1;

    public MyBlockingQueue(int size) {
        this.data = new Object[size];
    }

    public void add(Object obj) {
        lock.lock();
        try {
            while (count == this.data.length) {
                try {
                    addCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.data[++position] = obj;

            count++;
            takeCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while (count == 0) {
                try {
                    takeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Object obj = this.data[position--];
            count--;
            addCondition.signalAll();
            return obj;
        } finally {
            lock.unlock();
        }
    }
}