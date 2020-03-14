package com.lihao.pipeline;

public abstract class ValveBase implements Valve{

    public Valve next;
    public Valve getNext(){
        return next;
    }

    public void setNext(Valve v){
        next = v;
    }

    public abstract void invoke(String s);
}
