package com.lihao.thread.pattern.producerconsumer;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueChannel<P> implements Channel<P> {
    //私有阻塞队列
    private final BlockingQueue<P> queue;
    //带参构造方法
    public BlockingQueueChannel(BlockingQueue<P> queue){
        this.queue = queue;
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(P product) throws InterruptedException {
        queue.put(product);
    }
}
