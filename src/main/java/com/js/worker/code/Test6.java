package com.js.worker.code;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test6 {

	public static void main(String[] args) throws IOException, InterruptedException {
//		DataOutputStream br = new DataOutputStream(new FileOutputStream("C:\\\\work\\\\test\\\\test5.txt"));
//		while (true) {
//			String msgId = "12324556";
//			String taskId = "5";
//			String info = "jinshuangend";
//			int len = 4 + msgId.length() + taskId.length() + info.length();
//			br.writeShort(len);
//			br.writeByte(msgId.length());
//			br.writeByte(taskId.length());
//			br.writeBytes(msgId);
//			br.writeBytes(taskId);
//			br.writeBytes(info);
//			br.flush();
//			Thread.sleep(10);
//		}
		String str ="10:hugetlb:/system.slice/docker-be53d66a405007144f0c35efa3bf3e4774eabdcde3efc54e96362fbbc9e17e6a.scope";
		String str1 = str.split("docker-")[1];
		System.out.println(str1);
		String str2 = str1.split("\\.")[0];
		System.out.println(str2);
	}

}
