package com.lihao.serializeutil;

import com.lihao.nettymessage.AbstractSerialize;

import java.io.*;

public class JdkSerializeUtil extends AbstractSerialize {
    @Override
    public <T> byte[] serialize(T obj) {
        if(obj == null){
            throw new NullPointerException();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        try{
            ObjectInputStream ois = new ObjectInputStream(bis);
            T obj = (T) ois.readObject();
            return obj;
         }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
