package com.js.worker.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test8 {
	
	public static void main(String[] args) {
		Integer a = 100;
		Integer b = 130;
		Integer c = new Integer(100);
		int d = 100;
		Integer e = Integer.valueOf(100);
		Integer f = 130;
		System.out.println("==============test1");
		System.out.println("a == c :" + (a == c));
		System.out.println("a.equals(c) :" + a.equals(c));
		
		System.out.println("==============test2");
		System.out.println("b == f :" + (b == f));
		System.out.println("b.equals(f) :" + b.equals(f));
		
		System.out.println("==============test3");
		System.out.println("a == d :" + (a == d));
		System.out.println("a.equals(d) :" + a.equals(d));
		
		System.out.println("==============test4");
		System.out.println("a == e :" + (a == e));
		System.out.println("a.equals(e) :" + a.equals(e));
		
		System.out.println("==============test5");
		System.out.println("c == d :" + (c == d));
		System.out.println("c.equals(d) :" + c.equals(d));
	}

}
