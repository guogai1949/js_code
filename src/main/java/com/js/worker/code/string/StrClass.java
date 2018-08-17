package com.js.worker.code.string;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StrClass {
	
	public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Person person = new Person();
		Class[] clazzs = new Class[2];
		clazzs[0] = "str".getClass();
		Integer i =5;
		clazzs[1] = i.getClass();
		System.out.println(person.getClass());
		Method method = person.getClass().getDeclaredMethod("eat", clazzs);
		System.out.println(method);
		method.invoke(person, "chicken", (Integer)5);
	}

}
