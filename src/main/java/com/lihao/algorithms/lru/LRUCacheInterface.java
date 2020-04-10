package com.lihao.algorithms.lru;

import java.util.HashMap;

public interface LRUCacheInterface {
    //增加方法
    void set(String key, String value);
    //删除方法
    void remove(String key);
    //查询方法
    String get(String key);
}
