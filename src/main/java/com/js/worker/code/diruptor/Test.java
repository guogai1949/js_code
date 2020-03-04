package com.js.worker.code.diruptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
	
	public static void main(String args[]) {
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		
		for(String id : map.keySet()) {
			map.remove(id);
		}
	}

}
