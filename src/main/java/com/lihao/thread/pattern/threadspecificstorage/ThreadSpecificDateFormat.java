package com.lihao.thread.pattern.threadspecificstorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于ThreadSpecificStorage模式实现SimpleDateFormate的线程安全
 */
public class ThreadSpecificDateFormat {
    private static final ThreadLocal<SimpleDateFormat> TS_SDF = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static Date prase(String timeStamp, String format) throws ParseException {
        final SimpleDateFormat sdf = TS_SDF.get();
        sdf.applyPattern(format);
        Date date = sdf.parse(timeStamp);
        return date;
    }

    public static void main(String[] args) throws ParseException{
        Date date = ThreadSpecificDateFormat.prase("20150501123040", "yyyyMMddHHmmss");
        System.out.println(date);
    }

}
