package com.lihao.pipeline;

public class SecondValve extends ValveBase {

    @Override
    public void invoke(String s) {
        s = s.replace("22","second");
        System.out.println("after second valve handled: s = " + s);
        getNext().invoke(s);
    }
}
