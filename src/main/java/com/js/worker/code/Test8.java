package com.js.worker.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test8 {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Iterator<String> iterator = list.iterator();
		if(iterator.hasNext()) {
			String s = iterator.next();
			iterator.remove();
		}
	}

}
