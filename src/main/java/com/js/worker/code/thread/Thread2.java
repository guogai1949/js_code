package com.js.worker.code.thread;

public class Thread2 implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
