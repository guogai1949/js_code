package com.js.worker.code.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadDemo {
	
	public static void main(String args[]) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i=0; i<5; i++) {
			executorService.submit(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("========" + Thread.currentThread().getName());
				}
				
			});
		}
	}

}
