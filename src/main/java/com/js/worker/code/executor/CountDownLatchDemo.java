package com.js.worker.code.executor;

import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	
	static ExecutorService es = Executors.newCachedThreadPool();//线程池  
	
	static CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es);  
	
	public static class CountThread implements Runnable {
		
		private CountDownLatch latch;
		
		public CountThread(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"开始工作。");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"结束工作。");
			latch.countDown();
		}
		
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CountDownLatch latch=new CountDownLatch(4);
		for(int i=0; i<4;i++) {
			Thread thread = new Thread(new CountThread(latch));
			thread.setName("thread:"+ i);
			thread.start();
		}
		latch.await();		
		System.out.println("开始计算");
		cs.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return 1;
			}
			
		});
		System.out.println(cs.take().get());
	}
	
}
