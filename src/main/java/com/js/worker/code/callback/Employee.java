package com.js.worker.code.callback;

public class Employee {

	public void doWorker(Boss boss) {
		System.out.println("do work !!!");
		boss.giveCall();
	}
}
