package com.js.worker.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> container = new ArrayList<>();
        for(int index = 0; index < len - 2;index++) {
            if (nums[index] > 0) 
            	break;
            if (index > 0 && nums[index] == nums[index - 1]) 
            	continue;
            int tmp = 0 - nums[index];
            container.clear();
            List<Integer> item;
            int i = index + 1;
            int j = len - 1;
            while(i < j) {
                if(nums[i] + nums[j] == tmp) {
                    item = new ArrayList<>();
                    item.add(nums[index]);
                    item.add(nums[i]);
                    item.add(nums[j]);
                    result.add(item);
                    while(i < j && nums[i] == nums[i+1]) {
                            i++;
                    }
                    while(i < j && nums[j] == nums[j-1]) {
                           j--;
                    }
                     ++i; --j;
                } else if(nums[i] + nums[j] < tmp) {
                    ++i;
                } else {
                    --j;
                }
            }
        }
        return result;
    }
	

	public List<List<Integer>> getABC(int[] nums) {
		  List<List<Integer>> result = new LinkedList<>();

	        if (nums != null && nums.length > 2) {
	            // 先对数组进行排序
	            Arrays.sort(nums);
	            // i表示如果取第i个数作为结果
	            for (int i = 0; i < nums.length - 2; ) {
	                // 第二个数可能的起始位置
	                int j = i + 1;
	                // 第三个数可能是结束位置
	                int k = nums.length - 1;

	                while (j < k) {
	                    // 如果找到满足条件的解
	                    if (nums[j] + nums[k] == -nums[i]) {
	                        // 将结果加入到结果含集中
	                        List<Integer> list = new ArrayList<>(3);
	                        list.add(nums[i]);
	                        list.add(nums[j]);
	                        list.add(nums[k]);
	                        result.add(list);

	                        // 移动到下一个位置。找下一组解
	                        k--;
	                        j++;

	                        // 从左向右找第一个与之前处理的数不同的数的下标
	                        while (j < k && nums[j] == nums[j - 1]) {
	                            j++;
	                        }
	                        // 从右向左找第一个与之前处理的数不同的数的下标
	                        while (j < k && nums[k] == nums[k + 1]) {
	                            k--;
	                        }
	                    }
	                    // 和大于0
	                    else if (nums[j] + nums[k] > -nums[i]) {
	                        k--;
	                        // 从右向左找第一个与之前处理的数不同的数的下标
	                        while (j < k && nums[k] == nums[k + 1]) {
	                            k--;
	                        }
	                    }
	                    // 和小于0
	                    else {
	                        j++;
	                        // 从左向右找第一个与之前处理的数不同的数的下标
	                        while (j < k && nums[j] == nums[j - 1]) {
	                            j++;
	                        }
	                    }
	                }

	                // 指向下一个要处理的数
	                i++;
	                // 从左向右找第一个与之前处理的数不同的数的下标
	                while (i < nums.length - 2 && nums[i] == nums[i - 1]) {
	                    i++;
	                }
	            }
	        }

	        return result;
	}
	
	public static void main(String args[]) {
		App app =new App();
		int[] nums = {-1,2,0,-3,-2,3,4,-5,-2,-7,1};
		System.out.println(app.getABC(nums));
	}
}
