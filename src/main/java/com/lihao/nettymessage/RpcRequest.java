package com.lihao.nettymessage;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcRequest implements Serializable {
    private long requestId;
    private String interfaceName;
    private String methodName;
    private String[] parameterTypes;
    private Object[] parameters;
}
