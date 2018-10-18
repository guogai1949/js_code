package com.js.worker.code.outclass;

/*
 * 成员内部类
 *  成员内部类也是最普通的内部类，它是外围类的一个成员，所以他是可以无限制的访问外围类的所有 成员属性和方法，尽管是private的，
 *  但是外围类要访问内部类的成员属性和方法则需要通过内部类实例来访问。
 *  在成员内部类中要注意两点，
 *  第一：成员内部类中不能存在任何static的变量和方法；
 *  第二：成员内部类是依附于外围类的，所以只有先创建了外围类才能够创建内部类。
 */
public class OutClass {
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		System.out.println("age:" + age);
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public InnerClass getInnerClass() {
		return new InnerClass();
	}
	
	public class InnerClass {
		
		public InnerClass() {
			//使用外围类的属性
			name = "jinshuang";
			//使用外围类的方法
			setAge(25);
		}
		
		public void display() {
			System.out.println("name:" + name +",age:" + age);
		}
		
		public OutClass getOutClass() {
			return OutClass.this;
		}
	}
	
	public static void main(String args[]) {
		OutClass outClass = new OutClass();
		OutClass.InnerClass innerClass = outClass.new InnerClass();
		innerClass.display();
		innerClass.getOutClass().getAge();
	}

}
