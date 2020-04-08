package com.lihao.algorithms.leecode;

import java.util.LinkedList;
import java.util.List;

public class IsPalindrome {
    //回文数
    public boolean solution(int x){
        String str = String.valueOf(x);
        char[] array = str.toCharArray();
        int length = array.length;
        int halfLen = length / 2;
        for(int i = 0; i< halfLen; i++){
            if(!(array[i] == array[length-1-i])){
                return false;
            }
        }
        return true;
    }

    //回文链表
    public boolean solution(LinkedList list){
        for(int i = 0; i < (list.size()/2); i++){
            if(!list.get(i).equals(list.get(list.size()-1-i))){
                return false;
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        int a = 1992;
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.solution(a));
    }
}
