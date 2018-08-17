package com.js.worker.code.executor;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		
		ScheduledExecutorService executorService1 = new ScheduledThreadPoolExecutor(2);
		
//		executorService.scheduleAtFixedRate(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("time1 is " + new Date());
//				
//			}
//			
//		}, 1, 1, TimeUnit.SECONDS);
		
		for (int i = 0; i < 4; i++) {
			executorService1.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+ ":" + new Date());

				}

			}, 1, 1, TimeUnit.SECONDS);
		}
	}

}
