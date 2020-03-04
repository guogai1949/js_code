package com.js.worker.code.executor;

import java.util.HashMap;

public class ThreadLocalTest {
	
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	
	
	public static void main(String args[]) {
		new Thread(() -> {
			try {
				for(int i=0; i<100; i++) {
					threadLocal.set(i);
					System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
					Thread.sleep(200);
				}
			} catch(Exception e) {
				
			} finally {
				threadLocal.remove();
			}
		},"threadLocal1").start();
		
		
		new Thread(() -> {
			try {
				for(int i=0; i<100; i++) {
					threadLocal.set(i);
					System.out.println(Thread.currentThread().getName() + "====" + threadLocal.get());
					Thread.sleep(200);
				}
			} catch(Exception e) {
				
			} finally {
				threadLocal.remove();
			}
		},"threadLocal2").start();
	}

}
