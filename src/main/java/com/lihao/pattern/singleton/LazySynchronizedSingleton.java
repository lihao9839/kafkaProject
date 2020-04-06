package com.lihao.pattern.singleton;

/**
 * 同步加锁懒汉式
 */
public class LazySynchronizedSingleton {
    private static LazySynchronizedSingleton instance;

    private LazySynchronizedSingleton(){}

    public static synchronized LazySynchronizedSingleton getInstance(){
        if (instance == null) {
            instance = new LazySynchronizedSingleton();
        }
        return instance;
    }
}
