package com.lihao.thread.pattern.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDeadLockAvoidance {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,
            1,
            60,
            TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args){
        ThreadPoolDeadLockAvoidance me = new ThreadPoolDeadLockAvoidance();
        me.test("<This will NOT deadlock>");
    }

    public void test(final String message){
        Runnable taskA = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing TaskA...");
                Runnable taskB = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("TaskB processes " + message);
                    }
                };
                Future<?> result = threadPoolExecutor.submit(taskB);
                try{
                    result.get();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
                System.out.println("TaskA done.");
            }
        };
        threadPoolExecutor.submit(taskA);
    }
}
