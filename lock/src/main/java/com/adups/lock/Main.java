package com.adups.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fighter_lee
 * @date 2017/12/13
 */
public class Main {
    private static final String TAG = "Main";
    public static void main(String[]args) {
//        reentrantLock();
        readWriteLock();
    }

    private static void readWriteLock() {
        System.out.println("readWriteLock 开始");

        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }

    }

    private static void reentrantLock() {
        System.out.println("reentrantLock: 开始");

        final Output output = new Output();
        new Thread(new Runnable() {
            @Override
            public void run() {
                output.output("abcde");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                output.output("efgh");
            }
        }).start();
        System.out.println("reentrantLock: 完成");
    }

}

class Output{
    private static final String TAG = "Output";
    private ReentrantLock lock = new ReentrantLock();

    public void output(String msg) {
        lock.lock();
        for (int i = 0; i < msg.length(); i++) {
            System.out.println("output: "+msg.charAt(i));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock.unlock();
    }
}

/**
 * ReadWriteLock
 * writeLock不同线程操作会互斥
 * readLock 不会互斥 提升效率
 */
class Data {
    private int data;// 共享数据
    ReadWriteLock lock = new ReentrantReadWriteLock();
    public void set(int data) {
        System.out.println(Thread.currentThread().getName() + "准备写入数据");
        lock.writeLock().lock();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        System.out.println(Thread.currentThread().getName() + "写入" + this.data);
    }
    public void get() {
        System.out.println(Thread.currentThread().getName() + "准备读取数据");
        lock.readLock().lock();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "读取" + this.data);
    }
}
