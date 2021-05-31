package com.afair.auth.util;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WangBing
 * @date 2021/5/6 10:04
 */
public class TestUtils {
    private ReentrantLock reentrantLock = new ReentrantLock(false);

    public void testLock(){
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获得锁");
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
//        new Thread()
    }
}
