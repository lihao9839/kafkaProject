package com.lihao.nettymessage;

import java.io.Serializable;

public class NettyMessage implements Serializable {
    private com.lihao.nettymessage.Header header;
    private Object body;
}
