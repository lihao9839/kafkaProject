package com.lihao.algorithms.leecode;


import java.util.Arrays;
import java.util.Random;

/**
 * 1亿个数中，找出最大的1万个数
 */
public class FindTenThousandMax {

    public static int partition(int[] arr, int low, int high){
        int i = low;
        int j = high;

        int x = arr[low];

        while(i < j){
            while(arr[j] >= x && i < j){
                j--;
            }
            if(i < j){
                arr[i] = arr[j];
                i++;
            }
            while(arr[i] < x && i < j){
                i++;
            }

            if(i < j){
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = x;
        return i;
    }

    private static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int index = partition(arr, low, high);
            quickSort(arr, low, index-1);
            quickSort(arr, index +1, high);
        }
    }

    //快速排序算法
    public static void quickSort(int[] arr){
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr, low, high);
    }

    //产生随机整形数组
    public static int[] generateRandomArray(int cnt){
        int[] arr = new int[cnt];
        Random r = new Random();
        for(int i = 0; i < cnt; i++){
            arr[i] = r.nextInt(cnt);
        }
        return arr;
    }

    public static void main(String[] args){
        int[] arr = generateRandomArray(10000000);
        System.out.println(Arrays.toString(arr));
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
