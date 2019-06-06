package com.js.worker.code;

import java.io.File;

public class FileDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:\\test\\test1\\test11\\test111");
		if(!file.exists()) {
			file.mkdirs();
		}
	}

}
