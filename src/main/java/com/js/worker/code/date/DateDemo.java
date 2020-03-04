package com.js.worker.code.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {

	public static void main(String[] args) {
		SimpleDateFormat cstFormatter    = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'+0800' ");
		System.out.println(cstFormatter.format(new Date(System.currentTimeMillis())));
		System.out.println(cstFormatter.format(1560392641000l));
	}

}
