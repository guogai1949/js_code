package com.js.worker.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static int removeDuplicates(int[] nums) {
		Arrays.sort(nums);
		int num = 0;

		if (nums.length == 1) {
			num++;
		} else {
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == nums[i + 1]) {
					if (i + 1 == nums.length - 1) {
						nums[num] = nums[i + 1];
						num++;
					}
				} else {
					nums[num] = nums[i];
					num++;
					if (i + 1 == nums.length - 1) {
						nums[num] = nums[i + 1];
						num++;
					}

				}
			}
		}

		return num;

	}
	
	public int firstUniqChar(String s) {
        Map<String,Integer> record = new HashMap<String, Integer>();
        for(int i =0; i<s.length();i++) {
        	String key = s.substring(i, i+1);
        	if(record.containsKey(key)) {
        		int value = record.get(key) +1;
        		record.put(key, value);
        	}else {
        		System.out.println(key);
        		record.put(key, 0);
        	}
        }
        System.out.println("=====================");
        System.out.println(record.toString());
        for(String k : record.keySet()) {
        	System.out.println(k);
        	if(record.get(k) == 0) {
        		return s.indexOf(k);
        	}
        }
		return -1;
        
    }

	public static void main(String[] args) {
//		int[] nums = {-1};	
//		int len = Test.removeDuplicates(nums);
//		for(int i =0; i<len; i++) {
//			System.out.println(nums[i]);
//		}
		String string = "dasdadas sa,";
//		StringBuilder sb = new StringBuilder();
//		for(int i=string.length()-1;i>=0;i--) {
//			sb.append(string.substring(i, i+1));
//		}
//		System.out.println(sb.toString());
		Test test = new Test();
		System.out.println(test.firstUniqChar("leetcode"));
	}

}
