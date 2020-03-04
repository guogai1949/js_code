package com.js.worker.code.threadlocal;
public class ThreadLocalTest {

    public static void main(String[] args) {
        final ThreadLocal<String> threadLocal1 = new ThreadLocal<String>() {
        	protected String initialValue() {
      		  return "jinshuang";
      	  }
        };
        final ThreadLocal<Integer> threadLocal2 = new ThreadLocal<Integer>() {
        	protected Integer initialValue() {
        		  return 0;
        	  }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
                threadLocal1.set("A");
                threadLocal2.set(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
                threadLocal1.set("B");
                threadLocal2.set(2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(threadLocal1.get());
                System.out.println(threadLocal2.get());
            }
        }).start();
    }
}