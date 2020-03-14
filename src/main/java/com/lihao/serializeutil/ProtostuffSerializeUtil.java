package com.lihao.serializeutil;

import com.lihao.nettymessage.AbstractSerialize;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtostuffSerializeUtil extends AbstractSerialize {

    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    @Override
    public <T> byte[] serialize(T obj) {
        if(obj == null){
            throw new NullPointerException();
        }

        Class<T> clazz = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data = new byte[0];
        try{
            data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            buffer.clear();
        }
        return data;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    private static <T> Schema<T> getSchema(Class<T> clazz){
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if(schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if(schema != null) {
                schemaCache.put(clazz, schema);
            }
        }
        return schema;
    }
}
