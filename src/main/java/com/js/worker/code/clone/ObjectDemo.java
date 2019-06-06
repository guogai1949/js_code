package com.js.worker.code.clone;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ObjectDemo {
	
	private BlockingQueue<String> queue;

	public ObjectDemo(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}
	
	public BlockingQueue getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue queue) {
		this.queue = queue;
	}

	public static void main(String args[]) throws InterruptedException {
		Map<Integer, BlockingQueue<String>> map = new HashMap<Integer, BlockingQueue<String>>();
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
		queue.put("1");
		ObjectDemo demo = new ObjectDemo(queue);
		map.put(1, queue);
		map.put(2, queue);
		map.put(3, queue);
		for(int i : map.keySet()) {
			System.out.println(i);
			System.out.println(map.get(i).take());
		}
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				map.remove(1);
			}
			
		}).start();
	}

}
