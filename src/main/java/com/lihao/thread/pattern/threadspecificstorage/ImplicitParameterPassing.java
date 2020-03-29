package com.lihao.thread.pattern.threadspecificstorage;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.atomic.AtomicInteger;

public class ImplicitParameterPassing {
    public static void main(String[] args) throws InterruptedException{
        ClientThread thread;
        BusinessService bs = new BusinessService();
        for(int i = 0; i < Integer.valueOf(args[0]); i++){
            thread = new ClientThread("test", bs);
            thread.start();
            thread.join();
        }
    }


}

class ClientThread extends Thread{
    private final String message;
    private final BusinessService bs;
    private static final AtomicInteger SEQ = new AtomicInteger(0);

    public ClientThread(String message, BusinessService bs){
        this.message = message;
        this.bs = bs;
    }

    @Override
    public void run() {
        Context.INSTANCE.setTransactionId(SEQ.getAndIncrement());
        bs.service(message);
    }
}

class Context{
    private static final ThreadLocal<Integer> TS_OBJECT_PROXY = new ThreadLocal<Integer>();

    public static final Context INSTANCE = new Context();

    private Context(){

    }

    public Integer getTransactionId(){
        return TS_OBJECT_PROXY.get();
    }

    public void setTransactionId(Integer transactionId){
        TS_OBJECT_PROXY.set(transactionId);
    }

    public void reset(){
        TS_OBJECT_PROXY.remove();
    }
}

class BusinessService{
    public void service(String message){
        int transactionId = Context.INSTANCE.getTransactionId();
        System.out.println("processing transaction " + transactionId + "'s message: " + message);
    }
}
