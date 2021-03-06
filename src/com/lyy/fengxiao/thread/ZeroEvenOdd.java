package com.lyy.fengxiao.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {

    /**
     * 打印零与奇偶数：1116。
     * 假设有这么一个类：
     * class ZeroEvenOdd {
     *   public ZeroEvenOdd(int n) { ... }      // 构造函数
     *   public void zero(printNumber) { ... }  // 仅打印出 0
     *   public void even(printNumber) { ... }  // 仅打印出 偶数
     *   public void odd(printNumber) { ... }   // 仅打印出 奇数
     * }
     * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
     * 线程 A 将调用 zero()，它只输出 0 。
     * 线程 B 将调用 even()，它只输出偶数。
     * 线程 C 将调用 odd()，它只输出奇数。
     * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
     * 示例 1：
     * 输入：n = 2
     * 输出："0102"
     * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
     */
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    private final Lock lock=new ReentrantLock();
    private final Condition condition1=lock.newCondition();
    private final Condition condition2=lock.newCondition();
    private final Condition condition3=lock.newCondition();
    private int count=0;
    private boolean flag=true;
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i=0;i<n;){
                while (!flag){
                    condition1.await();
                }
                i++;
                printNumber.accept(0);
                count++;
                flag=false;
                if (count%2==0){
                    condition2.signalAll();
                }else {
                    condition3.signalAll();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i=2;i<=n;){
                while (flag){
                    condition2.await();
                }
                i+=2;
                printNumber.accept(count);
                flag=true;
                condition1.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i=1;i<=n;){
                while (flag){
                    condition3.await();
                }
                i+=2;
                printNumber.accept(count);
                flag=true;
                condition1.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd=new ZeroEvenOdd(4);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.print(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Zero");

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Even");

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Odd");

        thread.start();
        thread1.start();
        thread2.start();
    }
}
