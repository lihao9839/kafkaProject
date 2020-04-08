package com.lihao.algorithms.leecode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

 */
public class Reverse {
    public int reverse(int x) {
        List<Long> list = new ArrayList<>();
        int absX = Math.abs(x);
        for (int count = 1; absX - this.get10bei(count-1) >= 0; count++){
            long num = absX % this.get10bei(count) / this.get10bei( count-1);
            list.add(num);
        }
        BigDecimal result = new BigDecimal(0);
        for(int cnt = 0; cnt< list.size(); cnt++){
            result = result.add(new BigDecimal(list.get(cnt) * this.get10bei(list.size()-cnt-1)));
        }
        if(x > 0){
            int p = result.intValue();
            long px = result.longValue();
            return result.intValue();
        }
        return 0 - result.intValue();
    }

    private Long get10bei(int i){
        Long result = 1L;
        for(int j = 0; j< i; j++){
            result *= 10;
        }
        return result;
    }

    public int reverse2(int x){
        int result = 0;
        while(x != 0){
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if((newResult - tail) / 10 != result){
                return 0;
            }
            result = newResult;
            x = x /10;
        }
        return result;
    }

    public int reverse3(int x){
        String str = String.valueOf(x);
        if(x < 0){
            str = str.substring(1);
        }
        char[] array = str.toCharArray();
        for(int i = 0; i < (array.length/2); i++){
            char tempChar = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = tempChar;
        }
        String result = String.valueOf(array);
        if(x < 0){
            if(Long.valueOf(result) > Integer.MAX_VALUE){
                return 0;
            }
            return 0 - Integer.valueOf(result);
        }else{
            if(Long.valueOf(result) > Integer.MAX_VALUE){
                return 0;
            }
            return Integer.valueOf(result);
        }
    }

    public static void main(String[] args){
        int x = -2147483648;
        Reverse reverse = new Reverse();
        System.out.println(reverse.reverse3(x));
    }
}
