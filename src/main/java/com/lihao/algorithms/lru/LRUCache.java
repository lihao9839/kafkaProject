package com.lihao.algorithms.lru;

import java.util.HashMap;

/**
 * LRU Least Recently Used, 最近使用算法
 * LRU算法基于一种假设：长期不使用的数据，在未来被用到的几率也不大。因此当数据所占的内存达到一个阈值后，可以选择一处掉最近最少
 * 使用的数据来保存内存的高效使用，这是基于时间局部性原理的一个假设。LRU算法主要用于缓存算法，在节省资源的情况下提高数据访问效率。
 */
public class LRUCache {

    private Node head;
    private Node end;

    //存储上限
    private int limit;
    //底层存储结构
    private HashMap<String, Node> hashMap;

    public LRUCache(int limit){
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    /**
     * 尾部插入节点
     * @param node
     */
    private void addNode(Node node){
        if(end != null){
            end.next = node;
            node.pre = end;
            node.next = null;
        }
        end = node;
        if(head == null){
            head = node;
        }
    }

    //删除key
    private String removeNode(Node node){
        if(node == end){//尾部
            end = end.pre;
        }else if(node == head){//头部
            head = head.next;
        }else{//中间
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.getKey();
    }

    private void refreshNode(Node node){
        if(node == end){
            return;
        }
        removeNode(node);
        addNode(node);
    }

    public void remove(String key){
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }

    public void put(String key, String value){
        Node node = hashMap.get(key);
        if(node == null){
            if(hashMap.size() >= limit){
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        }else{
            node.setValue(value);
            refreshNode(node);
        }
    }

    public String get(String key){
        Node node = hashMap.get(key);
        if(node == null){
            return null;
        }
        refreshNode(node);
        return node.getValue();
    }

    public static void main(String[] args){
        LRUCache lruCache=new LRUCache(5);
        lruCache.put("001","用户1信息");
        lruCache.put("002","用户2信息");
        lruCache.put("003","用户3信息");
        lruCache.put("004","用户4信息");
        lruCache.put("005","用户5信息");
        lruCache.get("002");
        lruCache.put("004","用户2信息更新");
        lruCache.put("006","用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
