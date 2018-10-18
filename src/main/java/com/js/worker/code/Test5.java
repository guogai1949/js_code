package com.js.worker.code;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test5 {
	
	public static void main(String args[]) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream bs = new DataInputStream(new FileInputStream("C:\\work\\test\\test5.txt"));
		//		BufferedReader br = new BufferedReader(new FileReader("C:\\work\\test\\test5.txt"));
		
			byte[] cap = new byte[2];
			int index = 0;
			while (true) {
				int a = bs.read(cap, 0, cap.length);
				System.out.println("==============================");
		}
	}

}
