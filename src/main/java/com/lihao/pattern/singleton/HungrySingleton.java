package com.lihao.pattern.singleton;

/**
 * 饿汉式
 */
public class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){};
    public static HungrySingleton getInstance(){
        return instance;
    }
}
