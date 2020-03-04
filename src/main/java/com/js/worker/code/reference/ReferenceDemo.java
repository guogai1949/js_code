package com.js.worker.code.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceDemo {
	
	public static void main(String args[]) {
		WeakReference<String> weakReference = new WeakReference<String>(new String("弱引用"));
		System.out.println("weakReference:" + weakReference.get());
		
		String str = new String("Misout的博客");
		SoftReference<String> softReference = new SoftReference<String>(new String("软引用"));
		System.out.println("softReference:" + softReference.get());
		System.gc();
		if(weakReference.get() == null) {
		    System.out.println("weakReference已经被GC回收");
		}
		
		if(softReference.get() == null) {
		    System.out.println("softReference已经被GC回收");
		}
		
		System.out.println(str);
	}

}
