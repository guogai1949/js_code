package com.js.worker.code.outclass;

/*
 * 匿名内部类
 * 
 */
public class AnOutClass {
	
	public InnerClassInterface getInnerClass(final int num) {
		return new InnerClassInterface() {
			int age = num;
			@Override
			public int getAge() {
				// TODO Auto-generated method stub
				return age;
			}
			
		};
		
	}

	public static void main(String[] args) {
		AnOutClass outClass = new AnOutClass();
		InnerClassInterface innerClass = outClass.getInnerClass(25);
		System.out.println(innerClass.getAge());

	}

}
