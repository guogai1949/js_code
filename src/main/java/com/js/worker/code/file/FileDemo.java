package com.js.worker.code.file;

import java.io.File;
import java.util.Arrays;

public class FileDemo {

	public static void main(String[] args) {
		File dir = new File("C:\\work\\apache-maven-3.5.3\\repository\\redis\\clients\\jedis\\3.0.1");
		if(dir.exists()) {
			System.out.println("=====================");
		}
		String[] files = dir.list();
		System.out.println(Arrays.toString(files));
		
		File[] files1 = dir.listFiles();
		for(File file : files1) {
			System.out.println(file.getPath());
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getParent());
		}
		System.out.println(Arrays.toString(files1));

	}

}
