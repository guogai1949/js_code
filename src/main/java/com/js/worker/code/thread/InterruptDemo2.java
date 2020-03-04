package com.js.worker.code.thread;
public class InterruptDemo2 extends Thread {
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                System.out.println("线程执行休眠开始");
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
//            	break;
            }finally {
               //可以尝试注掉这句话
//                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String  []args) throws InterruptedException {
        InterruptDemo2 interruptDemo = new InterruptDemo2();
        interruptDemo.start();
        Thread.sleep(1000);
        System.out.println("主线程睡眠结束");
        interruptDemo.interrupt();
        System.out.println("执行中断请求");
    }
}