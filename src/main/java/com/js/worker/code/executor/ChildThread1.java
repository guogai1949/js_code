package com.js.worker.code.executor;

public class ChildThread1 extends ParentThread {
	
	
	
	
	public void run() {
		System.out.println("current childThread1:" + Thread.currentThread().getName());
		try {
//			Thread.currentThread().sleep(Long.MAX_VALUE);
			queue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
