package com.js.worker.code.builder;

public class TestDemo {
	
	public static void main(String args[]) {
		Director director = new Director();
		Human human = director.createHumanByDeretor(new SmartHuman());
		System.out.println(human.getHead());
		System.out.println(human.getBody());
		System.out.println(human.getHand());
		System.out.println(human.getFoot());

	}

}
