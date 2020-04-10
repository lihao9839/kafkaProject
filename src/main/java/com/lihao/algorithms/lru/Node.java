package com.lihao.algorithms.lru;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    public Node pre;
    public Node next;
    private String key;
    private String value;

    Node(String key, String value){
        this.key = key;
        this.value = value;
    }
}
