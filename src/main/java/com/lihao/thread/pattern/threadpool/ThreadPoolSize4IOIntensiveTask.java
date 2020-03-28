package com.lihao.thread.pattern.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 设置IO密集型任务的线程池
 */
public class ThreadPoolSize4IOIntensiveTask {

    public static void main(String[] args){
        ThreadPoolExecutor threadpool = new ThreadPoolExecutor(
                1,
                Runtime.getRuntime().availableProcessors() * 2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(200));
        threadpool.submit(new IOIntensiveTask());
    }

    private static class IOIntensiveTask implements Runnable{
        @Override
        public void run(){
            //执行大量的IO操作
        }
    }
}
