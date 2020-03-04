package com.js.worker.code.executor;

public class ThreadTest {
	
	public static void main(String args[]) {
		
		ChildThread1 thread10 = new ChildThread1();
		thread10.register();
		ChildThread1 thread11 = new ChildThread1();
		thread11.register();
		ChildThread1 thread12 = new ChildThread1();
		thread12.register();
		ChildThread1 thread13 = new ChildThread1();
		thread13.register();
		ChildThread2 thread2 = new ChildThread2();
		thread2.register();
	}

}
