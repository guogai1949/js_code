package com.js.worker.code.outclass;
/*
 * 静态内部类
 * 关键字static中提到Static可以修饰成员变量、方法、代码块，其他它还可以修饰内部类，
 * 使用static修饰的内部类我们称之为静态内部类，不过我们更喜欢称之为嵌套内部类。
 * 静态内部类与非静态内部类之间存在一个最大的区别，我们知道非静态内部类在编译完成之后会隐含地保存着一个引用，
 * 该引用是指向创建它的外围内，但是静态内部类却没有。没有这个引用就意味着：
 * 1、 它的创建是不需要依赖于外围类的。
 * 2、 它不能使用任何外围类的非static成员变量和方法。
 */
public class StaticOutClass {

	private String sex = "male";
	
	private static String name = "jinshung";
	
	static class InnerClass {
		 /* 在静态内部类中可以存在静态成员 */
		public static String name = "huihui";
		
		public void display() {
			System.out.println("name:" + name);
			//不能访问外部类的非静态成员变量
//			System.out.println("sex:" + sex);
		}
	}
	
	public void display() {
		 /* 外围类访问静态内部类：内部类. */
		System.out.println(InnerClass.name);
		 /* 静态内部类 可以直接创建实例不需要依赖于外围类 */
		new InnerClass().display();
	}
}
