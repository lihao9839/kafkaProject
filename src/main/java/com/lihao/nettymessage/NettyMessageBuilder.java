package com.lihao.nettymessage;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class NettyMessageBuilder {

    public static NettyMessage build(){
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        RpcRequest request = new RpcRequest();
        header.setCrcCode(1234);
        header.setType(TrayIcon.MessageType.INFO);
        header.setLength(10);
        header.setSessionId(200);

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("demoKey", (Object) "demoValue");
        header.setAttachment(map);

        request.setInterfaceName("com.demo");
        String[] types = {"java.lang.String", "java.lang.Integer"};
        String[] param = {"java.lang.String", "java.lang.Integer"};
        request.setParameters(types);
        request.setParameterTypes(param);
        request.setMethodName("buy");
        request.setRequestId(123456);
        return message;
    }
}
