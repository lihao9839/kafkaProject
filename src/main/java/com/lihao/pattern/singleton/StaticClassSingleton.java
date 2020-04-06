package com.lihao.pattern.singleton;

/**
 * 静态内部类单例
 */
public class StaticClassSingleton {

    private static class StaticClassSingletonHolder {
        private static final StaticClassSingleton INSTANCE = new StaticClassSingleton();
    }

    private StaticClassSingleton(){}

    public static final StaticClassSingleton getInstance(){
        return StaticClassSingletonHolder.INSTANCE;
    }
}
