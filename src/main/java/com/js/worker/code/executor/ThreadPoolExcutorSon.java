package com.js.worker.code.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExcutorSon extends ThreadPoolExecutor {

	public ThreadPoolExcutorSon(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	public void beforeExecute(Thread t, Runnable r) {
		t.start();
	}
	
	public void afterExecute(Runnable r, Throwable t) {
		System.out.println("commplement");
	}
	
	
	public static void main(String args[]) {
		ThreadPoolExcutorSon tp = new ThreadPoolExcutorSon(5,5,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("before");
				
			}
			
		});
		Runnable r = new Runnable() {

			@Override
			public void run() {
				System.out.println("running");
			}
			
		};
		tp.beforeExecute(t, null);
//		tp.execute(r);
	}

}
