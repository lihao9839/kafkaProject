package com.lihao.abstractlevel;

public class Watermelon extends Fruit{
    private  int waterDegree;

    public boolean isJuicy(){
        return waterDegree > 60;
    }

    @Override
    public boolean isTasty() {
        return isJuicy();
    }
}
