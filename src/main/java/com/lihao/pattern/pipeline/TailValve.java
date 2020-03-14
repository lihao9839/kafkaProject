package com.lihao.pipeline;

public class TailValve extends ValveBase {
    @Override
    public void invoke(String s) {
        s = s.replace("33", "third");
        System.out.println("after tail Valve handled : s = " +s);
    }
}
