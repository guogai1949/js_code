package com.js.worker.code.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test1 {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final Test1 test1 = new Test1();
         
        new Thread(){
            public void run() {
                test1.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                test1.get(Thread.currentThread());
            };
        }.start();
         
    }  
     
    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }
}