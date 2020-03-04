package com.js.worker.code.string;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StrClass {
	
	public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
		Person person = new Person("aadsad");
		Class[] clazzs = new Class[2];
		clazzs[0] = "str".getClass();
		Integer i =5;
		clazzs[1] = Class.forName("java.lang.Integer");
		System.out.println(person);
		Method method = person.getClass().getDeclaredMethod("eat", clazzs);
		System.out.println(method);
		method.invoke(person, "chicken", (Integer)5);
		
		Class clazz = Class.forName("com.js.worker.code.string.Person");
		for(Method m : clazz.getDeclaredMethods()) {
			System.out.println(m.getName());
		}
		ClassLoader classLoader = clazz.getClassLoader();
		System.out.println(classLoader);
		
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		System.out.println(field.get(person));
		field.set(person, "jinshuang");
		System.out.println(field.get(person));
		Constructor constructor = clazz.getConstructor(String.class);
		Person p = (Person) constructor.newInstance("jinshuang");
		System.out.println(p);
		System.out.println(Class.forName("java.lang.Object").getClassLoader());
		
		Class studentClass = Student.class;
		Field field1 = studentClass.getSuperclass().getDeclaredField("name");
		field1.setAccessible(true);
		Constructor constructor1 = studentClass.getConstructor(String.class);
		Student stu = (Student) constructor1.newInstance("123");
		field1.set(stu, "zhangsan");
		System.out.println(field1.get(stu));
	}

}
