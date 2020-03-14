package com.lihao.algorithm.leecode;

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

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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

    public static void main(String[] args){
        int x = 1534236469;
        Reverse r = new Reverse();
        System.out.println(r.reverse2(x));
    }
}
