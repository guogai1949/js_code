package com.js.worker.code.map;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(8,0.5f);
		
		Field threshold = map.getClass().getDeclaredField("threshold");
		Field modCount = map.getClass().getDeclaredField("modCount");
	    threshold.setAccessible(true);
	    modCount.setAccessible(true);
	    System.out.println("threshold:" + threshold.get(map));
	    map.put(1, 1);
	    map.put(2, 2);
	    map.put(3, 3);
	    map.put(9, 9);
	    map.put(10, 10);
	    map.put(4, 4);
	    Integer a = 1;
	    System.out.println("jinshuang".hashCode());
	    System.out.println("threshold:" + threshold.get(map));
	    System.out.println("modCount:" + modCount.get(map));
	    
	    for(Integer key : map.keySet()) {
	    	 System.out.print(key + " -> ");
	    }
	    
	    for(int i=0; i<3; i++) {
	    	map.remove(i);
	    }
	    System.out.println("threshold:" + threshold.get(map));
	    System.out.println("modCount:" + modCount.get(map));
	}

}
