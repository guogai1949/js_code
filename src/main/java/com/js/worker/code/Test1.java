package com.js.worker.code;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
	
	public void rotate(int[] nums, int k) {

		rotatePart(nums, 0, nums.length - k);
		rotatePart(nums, nums.length - k + 1, nums.length);
	}
	        

	private void rotatePart(int[] nums, int i, int j) {
		// TODO Auto-generated method stub
		
	}
	//0,1,0,3,12
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int index =1;
        for(int i =0; i<len-1;i++){
            if(nums[i] == 0) {
                while(nums[index] == 0){
                    index++;
                    if(index == len) {
                    	return;
                    }
                }
                nums[i] = nums[index];
                nums[index] = 0;
            }else {
                index = i+1;
            }
        }
    }
    
	public void rotate(int[][] matrix) {
	        
	        for(int i=0; i<matrix.length;i++){
	            for(int j=0; j<matrix[i].length;j++) {
	            	int t =matrix[i][j];
	            	matrix[i][j]=matrix[matrix.length-1-j][matrix[i].length-1-i];
	            	matrix[matrix.length-1-j][matrix[i].length-1-i] = t;
	            }
	        }
	        
	        for(int i=0; i<matrix.length;i++){
	            for(int j=0; j<matrix[i].length;j++) {
	            	int t1 = matrix[i][j];
	            	matrix[i][j]=matrix[i][matrix[i].length-1-j];
	            	matrix[i][matrix[i].length-1-j] = t1;
	            }
	        }
	        
	    }


	public static void main(String[] args) {
		int a = 5;
		Integer b = new Integer(5);
		Integer e = 5;

		System.out.println("1=================");
		System.out.println(a == b);
		System.out.println(a == e);
		
		String c = "abc";
		String d = new String("abc");
		System.out.println("2=================");
		if(c == d) {
			System.out.println("true");
		}
		
		Integer i = new Integer(128);
		Integer i2 = 128;
		System.out.println("3=================");
		System.out.println(i == i2);
		
		Integer i3 = new Integer(127);
		Integer i4 = 127;
		System.out.println("4=================");
		System.out.println(i3 == i4);
		

		Integer i5 = 128;
		Integer i6 = 128;
		System.out.println("5=================");
		System.out.println(i5 == i6);
		
		Test1 test = new Test1();
		int[] digits = {6,1,4,5,3,9,0,1,9,5,1,8,6,7,0,5,5,4,3};
		int[] digits1 = {0,1,0,3,12};
		System.out.println("6=================");
		test.moveZeroes(digits1);
		for(int j=0;j<digits1.length;j++) {
			System.out.println(digits1[j]);
		}
	}

}
