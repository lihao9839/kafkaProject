package com.lihao.pipeline;

public class FirstValve extends ValveBase {
    @Override
    public void invoke(String s) {
        s = s.replace("11", "first");
        System.out.println("after first valve handle: s = " + s);
        getNext().invoke(s);
    }
}
