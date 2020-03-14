package com.lihao.serializeutil;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.lihao.nettymessage.AbstractSerialize;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class HessianSerializeUtil extends AbstractSerialize {

    @Override
    public <T> byte[] serialize(T obj) {
        if(obj == null){
            throw new NullPointerException();
        }

        try{
            ByteArrayOutputStream  bos = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(bos);
            ho.writeObject(obj);
            return bos.toByteArray();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        if(data == null){
            throw new NullPointerException();
        }
        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(bis);
            return (T)hi.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
