package com.lihao.thread.pattern.twophrasetermination;

public enum AlarmType {
    FAULT("fault"), RESUME("resume");

    private final String name;

    private AlarmType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
