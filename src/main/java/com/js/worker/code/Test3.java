package com.js.worker.code;

public class Test3 {
	
	public void testCase(int x) {
		switch (x)
		{
		    case 1: System.out.println("Test1");
		    case 2:
		    case 3:
		        System.out.println("Test2");
		        break;
		    default:
		    System.out.println("Test3");
		    break;
		}
	}

	public static void main(String args[]) {
		Test3 test3 = new Test3();
		System.out.println("===============0");
		test3.testCase(0);
		System.out.println("===============1");
		test3.testCase(1);
		System.out.println("===============2");
		test3.testCase(2);
		System.out.println("===============3");
		test3.testCase(3);
	}
}
