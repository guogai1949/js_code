package com.js.worker.code.singletonpattern;

public class TestStream {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 该类只能有一个实例
	private TestStream() {
	} // 私有无参构造方法
		// 该类必须自行创建
		// 有2种方式

	private static TestStream ts1 = null;

	// 这个类必须自动向整个系统提供这个实例对象
	public static TestStream getTest() {
		if (ts1 == null) {
			ts1 = new TestStream();
		}
		return ts1;
	}

	public void getInfo() {
		System.out.println("output message:" + name);
	}

	public static void main(String[] args) {
		TestStream s = TestStream.getTest();
		s.setName("张孝祥 1");
		System.out.println(s.getName());
		TestStream s1 = TestStream.getTest();
		s1.setName("张孝祥 2");
		System.out.println(s1.getName());
		s.getInfo();
		s1.getInfo();
		if (s == s1) {
			System.out.println("创建的是同一个实例");
		} else if (s != s1) {
			System.out.println("创建的不是同一个实例");
		} else {
			System.out.println("application error");
		}
	}
}
