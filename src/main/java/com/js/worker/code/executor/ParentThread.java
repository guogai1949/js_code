package com.js.worker.code.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParentThread implements Runnable {
	
	private static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);
	
	protected static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(100);
	
	static {
		try {
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			queue.put(1);
			
			queue.put(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void register() {
    	threadPool.scheduleAtFixedRate(this, 1, 3000, TimeUnit.MILLISECONDS);
    }

	@Override
	public void run() {
		System.out.println("current parentThread:" + Thread.currentThread().getName());
		
	}

}
