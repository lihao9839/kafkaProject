package com.lihao.thread.pattern.threadspecificstorage;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ManagedThreadLocal<T> extends ThreadLocal<T> {

    private static volatile Queue<WeakReference<ManagedThreadLocal<?>>> instances =
            new ConcurrentLinkedDeque<WeakReference<ManagedThreadLocal<?>>>();
    private volatile ThreadLocal<T> threadLocal;

    private ManagedThreadLocal(final InitialValueProvider<T> ivp){
        this.threadLocal = new ThreadLocal<T>(){
            @Override
            protected T initialValue() {
                return ivp.initialValue();
            }
        };
    }

    public static <T> ManagedThreadLocal<T> newInstance(final InitialValueProvider<T> ivp){
        ManagedThreadLocal<T> mtl = new ManagedThreadLocal<T>(ivp);
        instances.add(new WeakReference<ManagedThreadLocal<?>>(mtl));
        return mtl;
    }

    public static <T> ManagedThreadLocal<T> newInstance(){
        return newInstance(new ManagedThreadLocal.InitialValueProvider<T>());
    }

    public T get(){
        return threadLocal.get();
    }

    public void set(T value){
        threadLocal.set(value);
    }

    public void remove(){
        if(null != threadLocal){
            threadLocal.remove();
            threadLocal = null;
        }
    }

    public static void removeAll(){
        WeakReference<ManagedThreadLocal<?>> wrMtl;
        ManagedThreadLocal<?> mtl;
        while(null != (wrMtl = instances.poll())){
            mtl = wrMtl.get();
            if(null != wrMtl){
                mtl.remove();
            }
        }
    }

    public static class InitialValueProvider<T>{
        protected T initialValue(){
            return null;
        }
    }
}
