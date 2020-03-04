package com.js.worker.code.callback.zkcurator;
public class Test {
    public static void doSomething() {
        System.out.println("线程【" + Thread.currentThread().getName() + "】正在运行...");
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                ZooKeeperLock lock = null;
                lock = new ZooKeeperLock("192.168.171.54:15560","/locks", "test1");
                if (lock.lock()) {
                    doSomething();
                    try {
                        Thread.sleep(1000);
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
        
    }
}