package com.lihao.pipeline;

public class Client {
    public static void main(String[] args){
        String s = "11,22,33";
        System.out.println("Input : " + s);
        StandardPipeline pipeline = new StandardPipeline();
        TailValve tail = new TailValve();
        FirstValve first = new FirstValve();
        SecondValve second = new SecondValve();

        pipeline.setTail(tail);
        pipeline.addValve(first);
        pipeline.addValve(second);

        pipeline.getHead().invoke(s);
    }
}
