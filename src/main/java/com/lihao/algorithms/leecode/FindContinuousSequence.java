package com.lihao.algorithms.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
*/
public class FindContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        List<int[]> resultList = new ArrayList<>();
        for(int i = 0; i<= target/2+1; i++) {
            for (int j = i, sum = 0; j <= target/2+1; j++) {
                sum += j;
                if (target == sum) {
                    int length = j-i;
                    int[] array = new int[length+1];
                    int count = 0;
                    while(count <= length){
                        array[count] = i+count;
                        count++;
                    }
                    resultList.add(array);
                }
            }
        }
        if(resultList != null){
            int[][] resultArray = new int[resultList.size()][];
            for(int i = 0; i < resultList.size(); i++){
                resultArray[i] = resultList.get(i);
            }
            return resultArray;
        }else{
            return new int[0][0];
        }
    }

    public static void main(String[] args){
        FindContinuousSequence fc = new FindContinuousSequence();
        int target = 73828;
        int[][] result = fc.findContinuousSequence(target);
        for(int cnt = 0; cnt< result.length;cnt++) {
            System.out.println(Arrays.toString(result[cnt]));
        }
    }
}
