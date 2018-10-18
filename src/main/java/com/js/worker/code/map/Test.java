package com.js.worker.code.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
	
	private static Map<String,String> map = new HashMap<String,String>();
	private static AtomicInteger count = new AtomicInteger(0);
	private volatile int i = 0;
	
	public void increase() {
		i++;
	}
	
	public static void main(String args[]) {
		Test test = new Test();
		for(int i=0; i<10; i++) {
			map.put(i+"",i+"");
		}
		
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					for(int j = 0; j< 10000; j++) {
						test.increase();
					}
				}

			});
			thread.setName("thread:"+i);
			thread.start();
		}
		
		while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.i);
	}

}
