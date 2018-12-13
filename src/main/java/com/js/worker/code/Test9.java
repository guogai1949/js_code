package com.js.worker.code;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test9 {
	
	private static BlockingQueue queue = new LinkedBlockingQueue<>();
	
	class Inner {
		
		void method() throws InterruptedException {
			System.out.println(queue.take());
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
//		System.out.println(Thread.currentThread().getName());
//		Thread thread = new Thread();
//		LockSupport.park();
//		thread.join();
//		thread.yield();
//		thread.start();
//		System.out.println(thread.getName());
		System.out.println(new Date());
		queue.put("1");
		queue.put("2");
		queue.put("3");
		Test9 test = new Test9();
		Inner inner = test.new Inner();
		inner.method();
		
	}

}
