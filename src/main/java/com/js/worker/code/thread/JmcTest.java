package com.js.worker.code.thread;

public class JmcTest {

	public volatile static boolean flag = true;

	public static void main(String args[]) {
		Thread t1 = new Thread(new Thread1());
		t1.setName("thread1");
		t1.start();
		Thread t2 = new Thread(new Thread2());
		t2.setName("thread2");
		t2.start();
		for (int i = 0; i < 1000; i++) {
			if (flag) {
				flag = false;
			} else {
				flag = true;
			}
			System.out.println(flag);
			System.out.println(t1.getState());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
