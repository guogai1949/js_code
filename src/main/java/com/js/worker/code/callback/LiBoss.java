package com.js.worker.code.callback;

public class LiBoss implements Boss {
	
	private String name;
	
	private Employee employee;
	
	LiBoss(String name, Employee employee) {
		this.name = name;
		this.employee = employee;
	}

	@Override
	public void giveWoker() {
		employee.doWorker(this);

	}

	@Override
	public void giveCall() {
		System.out.println("give call");

	}

}
