package com.js.worker.code.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test2 {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
     
    public static void main(String[] args)  {
        final Test2 test2 = new Test2();
         
        new Thread(){
            public void run() {
                test2.get(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
            public void run() {
                test2.get(Thread.currentThread());
            };
        }.start();
         
    }  
     
    public void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
             
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
