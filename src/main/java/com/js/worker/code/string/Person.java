package com.js.worker.code.string;

public class Person {
	
	private String name;
	private int age;
	
	public Person(String name) {
		this.name= name;
	}
	
	public void eat(String meat, Integer count) {
		System.out.println("吃很多肉");
	}
	
	public void dreak(String water) {
		System.out.println("喝水");
	}
	
	@AgeValidator(min=16, max=18)
	public void setAge(int age) {
		this.age = age;
	}

}
