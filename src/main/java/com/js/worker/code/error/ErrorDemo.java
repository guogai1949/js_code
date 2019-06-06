package com.js.worker.code.error;

public class ErrorDemo {
	
	public int count(int a , int b) {
		int c = 0;
		try {
			c= a/b;
		}catch(Exception e) {
			System.out.println("e0");
			throw new RuntimeException("===============");
		}
		return c;
	}
	
	public void count1(int a , int b) throws Exception {
		count(a, b);
	}
	
	public static void main(String args[]) {
		
		
		ErrorDemo test = new ErrorDemo();
		try {
			test.count1(10, 0);
		} catch (Exception e) {
			System.out.println("e1");
		}
	}

}
