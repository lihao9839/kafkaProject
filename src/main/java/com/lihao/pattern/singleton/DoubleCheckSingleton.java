package com.lihao.pattern.singleton;

/**
 * 双重加锁的懒汉单例
 */
public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance;
    private DoubleCheckSingleton(){}
    public static DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                instance = new DoubleCheckSingleton();
            }
        }
        return instance;
    }
}
