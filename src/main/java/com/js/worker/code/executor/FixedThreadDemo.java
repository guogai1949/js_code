package com.js.worker.code.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class FixedThreadDemo {
	
	public static void main(String args[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				return new Thread(r);
			}
		});
		
		ExecutorService executorService1 = Executors.newFixedThreadPool(5);
				
		for(int i=0; i<10; i++) {
			Future<?> future = executorService.submit(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("===="+ Thread.currentThread().getName());
				}
				
			});
		}
		
//		for(int i=0; i<10; i++) {
//			executorService1.submit(new Runnable() {
//
//				@Override
//				public void run() {
//					// TODO Auto-generated method stub
//					System.out.println("===="+ Thread.currentThread().getName());
//				}
//				
//			});
//		}
	}
}
