package com.js.worker.code.gc;

import java.lang.management.ManagementFactory;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		
		Thread.sleep(1000000000000L);

	}

}
