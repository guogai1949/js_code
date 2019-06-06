package com.js.worker.code.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		System.out.println(Test.class.getName());
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		
		executor.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				
			}
			
		}, 10, 10, TimeUnit.SECONDS);

	}

}
