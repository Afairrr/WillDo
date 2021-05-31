package com.afair.auth.util;

import java.util.concurrent.Semaphore;

/**
 * @author WangBing
 * @date 2021/5/7 9:53
 */
public class SemaphoreRunner {
    public static void main(String[] args) {
//        ReentrantLock
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore,"lala--"+i)).start();
        }
    }
    static final class Task extends Thread{
        Semaphore semaphore;

        public Task(Semaphore semaphore,String tname) {
            this.semaphore = semaphore;
            this.setName(tname);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"acquire at time"+System.currentTimeMillis());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"release at time"+System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

