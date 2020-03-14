package com.lihao.pipeline;

public interface Pipeline {
    public Valve getHead();
    public Valve getTail();
    public void setTail(Valve v);
    public void addValve(Valve v);
}
