package com.js.worker.code.threadlocal;

import java.util.concurrent.Semaphore;

public class ThreadLocalExample {
	
    private static ThreadLocal threadLocal = new ThreadLocal();

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
        	System.out.println("=================================");
        	System.out.println(threadLocal.get());
            threadLocal.set((int) (Math.random() * 100D));
            try {
            Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(threadLocal.get());
        }
        
        public ThreadLocal getThreadLocal() {
        	return threadLocal;
        }
    }

    public static void main(String[] args) throws InterruptedException {
         MyRunnable sharedRunnableInstance = new MyRunnable();
         Thread thread1 = new Thread(sharedRunnableInstance);
         Thread thread2 = new Thread(sharedRunnableInstance);
         thread1.start();
         thread2.start();
         Thread.currentThread().sleep(5000);
         System.out.println(sharedRunnableInstance.getThreadLocal().get());
    }

}