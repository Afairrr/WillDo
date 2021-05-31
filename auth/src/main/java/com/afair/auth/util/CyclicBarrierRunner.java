package com.afair.auth.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author WangBing
 * @date 2021/5/8 10:31
 */
public class CyclicBarrierRunner implements Runnable{
    private CyclicBarrier cyclicBarrier;

    private int index;

    public CyclicBarrierRunner(CyclicBarrier cyclicBarrier, int index) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            System.out.println("index"+index);
            index--;
            cyclicBarrier.await();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(11,()-> System.out.println("开始"));
        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarrierRunner(cyclicBarrier1,i)).start();
        }
        cyclicBarrier1.await();
        System.out.println("全部到达");
    }
}
