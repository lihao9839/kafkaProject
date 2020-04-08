package com.lihao.thread.pattern.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 基于Semaphore的支持流量控制的通道实现
 * @param <P>
 */
public class SemaphoreBaseChannel<P> implements Channel<P> {
    private final BlockingQueue<P> queue;
    private final Semaphore semaphore;

    /**
     *
     * @param queue 无界阻塞队列
     * @param flowLimit 流量限制数
     */
    public SemaphoreBaseChannel(BlockingQueue<P> queue, int flowLimit){
        this.queue = queue;
        this.semaphore = new Semaphore(flowLimit);
    }

    @Override
    public P take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public void put(P product) throws InterruptedException {
        semaphore.acquire();
        try{
            queue.put(product);
        }finally {
            semaphore.release();
        }
    }
}
