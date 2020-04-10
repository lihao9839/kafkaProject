package com.lihao.algorithms.lru;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    public Node pre;//前一个节点
    public Node next;//后一个节点
    private String key;
    private String value;

    Node(String key, String value){
        this.key = key;
        this.value = value;
    }
}
