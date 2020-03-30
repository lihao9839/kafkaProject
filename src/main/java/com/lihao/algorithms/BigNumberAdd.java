package com.lihao.algorithms;

import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

/**
 * 两个大数相加，非常大的数
 * 同时为整数，同时为负数，也可能有正有负。
 */
public class BigNumberAdd {

    public String solution(String str1, String str2){
        String result = "";
        //1、对入参进行数据截取，每段的截取长度10位长度进行截取；
        int splitCount = 10;
        int length1 = str1.length();
        int length2 = str2.length();
        int length = length2;//最长长度
        if(length1 > length2){
            length = length1;
        }
        int cnt = length / 10 + 1;
        String[] str1Array = new String[cnt];
        String[] str2Array = new String[cnt];
        for(int i = 0; i < cnt; i++){
            if(str1.length() > i*splitCount) {
                str1Array[i] = str1.substring(i * splitCount, (i + 1) * splitCount);
            }else{
                //填充0
                str1Array[i] = "0000000000";
            }

            if(str2.length() > i * splitCount) {
                str2Array[i] = str2.substring(i * splitCount, (i + 1) * splitCount);
            }else{
                str2Array[i] = "0000000000";
            }
        }
        //2、截取完毕后，分别进行对应段相加，保留进位情况
        int[] jinweiArray = new int[cnt];//进位值
        String resultArray[] = new String[cnt];
        for(int i = cnt-1; i > 0; i--){
            int big1 = Integer.getInteger(str1Array[i]);
            int big2 = Integer.getInteger(str2Array[i]);
            int resultBig = big1 + big2;
            String resultSplit = String.valueOf(resultBig);
            if(resultSplit.length() > splitCount){
                jinweiArray[i] = 1;
                resultArray[i] = resultSplit.substring(1);
            }else{
                resultArray[i] = resultSplit;
            }
        }
        //3、对数据段进行整合，考虑进位情况和补零情况
        for(int i = cnt-1; i > 0; i--){

        }
        //4、输出结果
        return result;
    }
}
