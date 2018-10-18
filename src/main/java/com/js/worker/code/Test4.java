package com.js.worker.code;

public class Test4 {
	
	public int inc() {
		int x;
		try {
			x = 1;
			return x;
		}catch(Exception e) {
			x = 2;
			return x;
		}finally {
			x = 3;
//			return x;
		}
	}
	
	public static void main(String args[]) {
		Test4 test4 = new Test4();
		System.out.println(test4.inc());
	}

}
