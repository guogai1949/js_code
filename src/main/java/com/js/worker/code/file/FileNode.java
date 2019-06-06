package com.js.worker.code.file;

import java.io.File;

public class FileNode {
	
	public static void main(String[] args) {
		File file = new File("C:\\test\\test1\\test11\\test111\\2.1.1\\topologyTest1");
		//文件对象实际存在,并且是目录，递归方法清晰的显示包含的所有文件、目录
		if(file.exists()) {
			getDeptTree(file,"");
		}
	}
	protected static void getDeptTree(File f, String prefix) {
		if(f.listFiles() != null){
			//获取目录下的所有文件和子目录
			File[] childs = f.listFiles();
			//遍历file数组
			for (int i = 0; i < childs.length;i++) {
				String strLine = "";
				String strStart = "";
				if (childs.length - i > 1) {
					strStart = prefix + "  │";
				} else {
					strStart = prefix + "  ";
				}
				if (i == childs.length - 1) {
					strLine = prefix + "  └─";
				} else {
					strLine = prefix + "  ├─";
				}
				//如果是文件，打印文件名及大小
				if(childs[i].isFile()) {
					System.out.println(strLine + childs[i].getName()+"  "+childs[i].length()+"B");
				}
				//如果是目录，打印目录名，执行递归方法
				if (childs[i].isDirectory()) {
					System.out.println(strLine + childs[i].getName());
					getDeptTree(childs[i], strStart);
				}
			}
		}
	}

}
