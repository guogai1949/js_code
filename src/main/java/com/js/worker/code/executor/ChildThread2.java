package com.js.worker.code.executor;

public class ChildThread2 extends ParentThread {
	
	
	public void run() {
		System.out.println("current childThread2:" + Thread.currentThread().getName());
		
	}

}
