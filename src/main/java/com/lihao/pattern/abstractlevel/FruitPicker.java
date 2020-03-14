package com.lihao.abstractlevel;

import java.util.List;
import java.util.stream.Collectors;

public class FruitPicker {

    public List<Fruit> pickGood(List<Fruit> fruitList){
        return fruitList.stream().filter(e -> check(e)).collect(Collectors.toList());
    }

    private boolean check(Fruit e){
        return e.isTasty();
    }
}
