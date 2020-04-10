package com.lihao.algorithms.lru;

import java.util.HashMap;

/**
 * LRU Least Recently Used, 最近最少使用算法
 * LRU算法基于一种假设：长期不使用的数据，在未来被用到的几率也不大。因此当数据所占的内存达到一个阈值后，可以选择一处掉最近最少
 * 使用的数据来保存内存的高效使用，这是基于时间局部性原理的一个假设。LRU算法主要用于缓存算法，在节省资源的情况下提高数据访问效率。
 *
 * 算法核心：基于HashMap，同时维护一个双向链表，通过链表的顺序关系，表示最近最少使用。
 */
public class LRUCache implements LRUCacheInterface{

    //头部节点，最旧
    private Node head;
    //尾部节点，最新
    private Node end;
    //存储上限
    private int limit;
    //底层存储结构基于HashMap
    private HashMap<String, Node> hashMap;
    //初始化构造方法
    public LRUCache(int limit){
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    @Override
    public void remove(String key){
        Node node = hashMap.get(key);
        if(node != null) {
            removeNode(node);//删除链表中值
            hashMap.remove(key);//删除底层存储值
        }
    }

    @Override
    public void set(String key, String value){
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

    @Override
    public String get(String key){
        Node node = hashMap.get(key);
        if(node == null){
            return null;
        }
        refreshNode(node);
        return node.getValue();
    }

    /**
     * 尾部插入节点
     * @param node 节点
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

    //删除节点
    private String removeNode(Node node){
        if(node == end){//尾部
            end = end.pre;
        }else if(node == head){//头部
            head = head.next;
        }else{//中间
            node.pre.next = node.next;//修改前后Node引用
            node.next.pre = node.pre;//修改后前Node引用
        }
        return node.getKey();
    }

    //刷新节点
    private void refreshNode(Node node){
        if(node == end){//最新不用管
            return;
        }
        //通过删除再添加方式，刷新节点顺序
        removeNode(node);
        addNode(node);
    }

    public static void main(String[] args){
        LRUCache lruCache=new LRUCache(5);
        lruCache.set("001","用户1信息");
        lruCache.set("002","用户2信息");
        lruCache.set("003","用户3信息");
        lruCache.set("004","用户4信息");
        lruCache.set("005","用户5信息");
        lruCache.get("002");
        lruCache.set("004","用户2信息更新");
        lruCache.set("006","用户6信息");
        System.out.println(lruCache.get("001"));
        System.out.println(lruCache.get("006"));
    }
}
