package com.lihao.algorithms.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap数据结构 构成LRU算法结构实现方式
 * @param <K>
 * @param <V>
 */
public class LFUCache2<K, V> extends LinkedHashMap<K, V> {
    private final int capcity;

    public LFUCache2(int capcity){
        super(8,0.75f, true);
        this.capcity = capcity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if(size() > capcity){
            System.out.println("移除的元素为：" + eldest.getValue());
        }
        return size() > capcity;
    }

    public static void main(String[] args){
        Map<Integer, Integer> map = new LFUCache2<>(5);
        for(int i = 1; i <12; i++){
            map.put(i,i);
            System.out.println("cache的容量为：" + map.size());
            if(i == 4){
                map.get(1);
            }
            System.out.println("剩余元素为");
            map.entrySet().forEach(integerIntegerEntry ->
                    System.out.println(integerIntegerEntry.getValue()));
        }
    }
}
