package com.js.worker.code.thread;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		while (true) {
			boolean flag = JmcTest.flag;
			if (flag) {
				// System.out.println("11");
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			} else {
				System.out.println("sleep");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
