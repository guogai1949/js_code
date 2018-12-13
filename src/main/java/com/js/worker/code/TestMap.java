package com.js.worker.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.js.worker.code.string.Person;

public class TestMap {
	
	public static void main(String args[]) {

		Map<String, Person> map = new HashMap<String, Person>();
		
		map.put("name1", new Person("name1"));
		map.put("name2", new Person("name2"));
		map.put("name3", new Person("name3"));
		map.put("name4", new Person("name4"));
		map.put("name5", new Person("name5"));
		map.put("name6", new Person("name6"));
		List<Person> list = null;
		if(map.values() instanceof List) 
			System.out.println("2222222222222222");
		else 
			list = new ArrayList<Person>(map.values());
		
		String str = "asdasd 123124   aaaaa  dsasadadassad     qqqqqqqqqqqq";
        String[] str1 = str.split("\\s+");
        for (int i =0; i<str1.length; i++) {
            System.out.println(str1[i]);
        }
		
		
        System.out.println(System.currentTimeMillis());
        Map<String, AtomicBoolean> isActives = new HashMap<String, AtomicBoolean>();
        isActives.put("23", new AtomicBoolean(false));
        
        AtomicBoolean isActive = isActives.get("23");
//        isActive.set(true);
        
        System.out.println(isActives.get("23").get());
        
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(new Date(1544514649000l));
        
		
	}
	
	
}
