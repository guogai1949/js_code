package com.js.worker.code.generic;

public class Test {
	
	public static void main(String args[]) {
		Generic<Number> number = new Generic<Number>(123);
		
		System.out.println(number.getKey());
		
		Generic<Integer> integer = new Generic<Integer>(345);
		
		System.out.println(integer.getKey());
		
		number.showKeyValue(integer);
		
		integer.showKeyValue(number);
	}

}
