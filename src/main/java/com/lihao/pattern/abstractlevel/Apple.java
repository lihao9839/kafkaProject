package com.lihao.abstractlevel;

public class Apple extends Fruit{
    private int sweetDegree;

    public boolean isSweet(){
        return sweetDegree > 60;
    }

    @Override
    public boolean isTasty() {
        return isSweet();
    }
}
