package com.js.worker.code.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(10);
		
		for(int i=0; i<5; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							semaphore.acquire();
							System.out.println("1=================="+Thread.currentThread().getName() + ":" + semaphore.availablePermits());
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
					
				}
				
			}).start();
		}
		
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (true) {
//					semaphore.release();
//					try {
//						Thread.sleep(2);
//						System.out.println("2=================="+Thread.currentThread().getName() + ":" + semaphore.availablePermits());
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
//				}
//			}
//			
//		}).start();

	}

}
