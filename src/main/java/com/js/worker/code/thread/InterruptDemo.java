package com.js.worker.code.thread;
public class InterruptDemo extends Thread {
    @Override
    public void run() {
        Thread.currentThread().interrupt();
        System.out.println("子线程执行中断请求结果1："+Thread.currentThread().isInterrupted());
        System.out.println("子线程执行中断请求结果2："+Thread.currentThread().isInterrupted());
    }

    public static void main(String  []args) throws InterruptedException {
        InterruptDemo interruptDemo = new InterruptDemo();
        interruptDemo.start();
        Thread.currentThread().sleep(1000);
        Thread.currentThread().interrupt();
        System.out.println("主线程执行中断请求结果1："+Thread.currentThread().isInterrupted());
        System.out.println("主线程执行中断请求结果2："+Thread.currentThread().isInterrupted());
        System.out.println("主线程执行中断请求结果3："+Thread.interrupted());
        //因为Thread会重置主线程终端状态所以第二次请求为false
        Thread.currentThread().interrupt();
        System.out.println("主线程执行中断请求结果4："+Thread.interrupted());
        
    }
}