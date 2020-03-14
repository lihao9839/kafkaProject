package com.lihao.serializeutil;

import com.alibaba.fastjson.JSON;
import com.lihao.nettymessage.AbstractSerialize;

public class FastjsonSerializeUtil extends AbstractSerialize {
    @Override
    public <T> byte[] serialize(T obj) {

        if(obj == null){
            throw new NullPointerException();
        }
        String json = JSON.toJSONString(obj);
        byte[] data = json.getBytes();
        return data;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        T obj = JSON.parseObject(new String(data), clazz);
        return obj;
    }
}
