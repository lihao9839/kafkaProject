package com.lihao.algorithms.lfu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * LFU Least Frequently Used 淘汰一定时期内被访问次数最少的元素。如果元素一定时间内的访问次数相同，则比较他们的最新一次访问
 * 时间。
 */
public class LFUCache<K, V> {
    private final int capcity;
    private Map<K, V> cache = new HashMap<>();
    private Map<K, HitRate>  count = new HashMap<>();

    public LFUCache(int capcity){
        this.capcity = capcity;
    }

    public void put(K key, V value){
        V v = cache.get(key);
        if(v == null){
            if(cache.size() == capcity){
                removeElement();
            }
            count.put(key, new HitRate(key, 1, System.nanoTime()));
        }else{
            addHitCount(key);
        }
        cache.put(key, value);
    }

    public V get(K key){
        V value = cache.get(key);
        if(value != null){
            addHitCount(key);
            return value;
        }
        return null;
    }

    //移除元素
    public void removeElement(){
        HitRate hr = Collections.min(count.values());
        cache.remove(hr.key);
        count.remove(hr.key);
    }

    //更新访问元素状态
    private void addHitCount(K key){
        HitRate hitRate = count.get(key);
        hitRate.hitCount = hitRate.hitCount + 1;
        hitRate.lastTime = System.nanoTime();
    }

    //内部类
    class HitRate implements Comparable<HitRate>{
        private K key;
        private int hitCount;
        private long lastTime;

        private HitRate(K key, int hitCount, long lastTime){
            this.hitCount = hitCount;
            this.key = key;
            this.lastTime = lastTime;
        }

        @Override
        public int compareTo(HitRate o) {
            int compare = Integer.compare(this.hitCount, o.hitCount);
            return compare == 0? Long.compare(this.lastTime, o.lastTime): compare;
        }
    }
}
