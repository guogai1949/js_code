package com.js.worker.code.thread;
public class WaitDemo {

    public static void main(String []args) throws InterruptedException {
        WaitDemo waitDemo = new WaitDemo();
        Thread thread1 = new Thread("t1"){
            @Override
            public void run(){
                synchronized (waitDemo){
                    try {
                        System.out.println("t1进入等待");
                        waitDemo.wait();
                       //我们没有写对象的唤醒，所以这句话不会输出出来
                        System.out.println("t1等待结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread thread2 = new Thread("t2"){
            @Override
            public void run(){
                synchronized (waitDemo){
                   //因为wait会释放掉waitDemo的内置锁，所以可以显示这句话会第二输出
                    System.out.println("t2进来了");
                    waitDemo.notify();
                    
                    try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println("t2执行结束");
                }
                
                System.out.println("t2状态end");
                
            }
        };
        thread1.start();
        Thread.sleep(1000);
        thread2.start();

    }

}