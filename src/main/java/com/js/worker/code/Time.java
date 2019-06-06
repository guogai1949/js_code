package com.js.worker.code;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Time {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));
		
		List list = new ArrayList<String>();
		list.add("sdasds");
		System.out.println(list);
		
		String str = null;
		System.out.println(str.getBytes().length);
	}

}
