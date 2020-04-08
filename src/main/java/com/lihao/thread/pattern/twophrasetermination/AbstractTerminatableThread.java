package com.lihao.thread.pattern.twophrasetermination;

/**
 * 可停止的抽象线程
 * 模式角色：Two-phaseTermination.AbstractTerminatableThread
 */
public abstract class AbstractTerminatableThread extends Thread implements Terminatable {

    //模式角色：Two-phaseTermination.TerminationToken
    public final TerminationToken terminationToken;

    public AbstractTerminatableThread(){
        this(new TerminationToken());
    }

    /**
     * 线程间共享的线程终止标示实例
     * @param terminationToken
     */
    public AbstractTerminatableThread(TerminationToken terminationToken){
        super();
        this.terminationToken = terminationToken;
        terminationToken.register(this);
    }

    /**
     * 留给子类实现其线程处理逻辑
     * @throws Exception
     */
    protected abstract void doRun() throws Exception;

    /**
     * 留个自雷实现，用于实现线程停止后的一些清理动作
     * @param cause
     */
    protected void doCleanup(Exception cause){

    }

    /**
     * 留给子类实现，用于执行线程停止所需的动作
     */
    protected  void doTerminate(){

    }

    @Override
    public void run() {
        Exception ex = null;
        try{
            for(;;){
                if(terminationToken.isToShutdown() && terminationToken.reservations.get() <=0){
                    break;
                }
                doRun();
            }
        }catch (Exception e){
            ex = e;
        }finally {
            try{
                doCleanup(ex);
            }finally {
                terminationToken.notifyThreadTermination(this);
            }
        }
    }

    @Override
    public void interrupt() {
        terminate();
    }

    /**
     * 请求停止线程
     */
    @Override
    public void terminate(){
        terminationToken.setToShutdown(true);
        try{
            doTerminate();
        }finally {
            if(terminationToken.reservations.get() <= 0){
                super.interrupt();
            }
        }
    }

    public void terminate(boolean waitUtilThreadTerminated){
        terminate();;
        if(waitUtilThreadTerminated){
            try{
                this.join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
