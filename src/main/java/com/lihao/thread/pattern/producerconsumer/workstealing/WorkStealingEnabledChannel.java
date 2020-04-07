package com.lihao.thread.pattern.producerconsumer.workstealing;

import com.lihao.thread.pattern.producerconsumer.Channel;

import java.util.concurrent.BlockingDeque;

public interface WorkStealingEnabledChannel<P> extends Channel<P> {
    P take(BlockingDeque<P> perferredQueue) throws InterruptedException;
}
