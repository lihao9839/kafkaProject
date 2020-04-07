package com.lihao.thread.pattern.producerconsumer;

/**
 * 产品类型
 * @param <P>
 */
public interface Channel<P> {
    //从通道里取出一个产品
    P take() throws InterruptedException;
    //放入通道中一个产品
    void put(P product) throws InterruptedException;
}
