package com.js.worker.code;

import java.util.concurrent.locks.LockSupport;

public class Test9 {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName());
		Thread thread = new Thread();
		LockSupport.park();
		thread.join();
		thread.yield();
		thread.start();
		System.out.println(thread.getName());
		
	}

}
