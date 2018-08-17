package com.js.worker.code.prototypepattern;

public class Circle extends Shape {
	 
	   public Circle(){
	     type = "Circle";
	   }
	 
	   @Override
	   public void draw() {
	      System.out.println("Inside Circle::draw() method.");
	   }
	}
