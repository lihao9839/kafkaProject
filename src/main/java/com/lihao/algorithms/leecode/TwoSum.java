package com.lihao.algorithm.leecode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i<nums.length; i++){
            int first = nums[i];
            for(int j = i++; j< nums.length; j++){
                int second = nums[j];
                if(target == (first + second)){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        for(int cnt = 0; cnt < nums.length; cnt++){
            Integer value= map.get(target - nums[cnt]);
            if(value != null && cnt != value.intValue()){
                return new int[]{cnt, map.get(target - nums[cnt])};
            }
        }
        return null;
    }

    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
            Integer value = map.get(target - nums[i]);
            if(value != null && value != i){
                return new int[]{i, value};
            }
        }
        return null;
    }

    public static void main(String[] args){
        TwoSum s = new TwoSum();
        int[] nums = new int[]{3,2,4};
        int target = 6;
        int[] result = s.twoSum3(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
