package com.js.worker.code.executor;

public class JoinDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
					try {
						Thread.currentThread().sleep(1000);
						System.out.println("===============sleep 1s");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
		});
		thread.setName("thread-demo-1");
		thread.start();
		System.out.println(Thread.currentThread().getName());
		Thread.currentThread().join();
		System.out.println("====================end");
	}

}
