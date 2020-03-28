package com.lihao.thread.pattern.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池饱和处理策略，支持将提交失败的任务重新放入线程池工作队列。
 */
public class ReEnqueueRejectedExcutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(executor.isShutdown()){
            return;
        }
        try{
            executor.getQueue().put(r);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
