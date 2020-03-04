package com.js.worker.code.threadlocal;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) throws InterruptedException {
        final Person person=new Person();
        Semaphore semaphore = new Semaphore(1);
        person.setName("jin");
        //这样做其实就是在操作同一个对象，如果需要实现多线程应该像下下面的注释一样，这样就针对于每一个线程创建一个独立的Person对象
          final ThreadLocal<Person> t=new ThreadLocal<Person>() {
        	  
        	  protected Person initialValue() {
//        		  Person person = new Person();
//        		  person.setName("jin");
        		  return null;
        	  }
        	  
          };
//        final ThreadLocal<Person> t=new ThreadLocal<Person>();

        for(int i=0;i<3;i++)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                	try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    int random = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + "==" + t.get());
                    t.get().setName(random + "");
                    System.out.println(Thread.currentThread().getName() + "==" + t.get().getName());
                    semaphore.release();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + ":" + t.get());
    }
}