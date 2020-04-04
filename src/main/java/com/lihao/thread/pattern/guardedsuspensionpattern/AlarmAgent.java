package com.lihao.thread.pattern.guardedsuspensionpattern;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 负责连接告警服务，并发送告警信息至服务器
 */
public class AlarmAgent {
    private volatile boolean connectedToServer = false;

//    private final Predicate agentConnected = new Predicate() {
//        @Override
//        public boolean evaluate() {
//            return connectedToServer;
//        }
//    };

    private final Predicate agentConnected = () -> connectedToServer;

    private final Blocker blocker = new ConditionVarBlocker();

    private final Timer heartbeatTimer = new Timer(true);

    public  void sendAlarm(final AlarmInfo alarm) throws Exception{
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    private void doSendAlarm(AlarmInfo alarmInfo) throws Exception{
        System.out.println("sending alarm" + alarmInfo);

        try{
            Thread.sleep(50);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void init(){
        Thread connectingThread = new Thread(new ConnectingTask());
    }

    protected void onConnected(){
        try{
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    System.out.println("connected to server");
                    return Boolean.TRUE;
                }
            });
//            blocker.signalAfter(new Callable<Boolean>() {
//                @Override
//                public Boolean call() throws Exception {
//                    connectedToServer = true;
//                    System.out.println("connected to server");
//                    return Boolean.TRUE;
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void onDisconnected(){
        connectedToServer = false;
    }

    private class ConnectingTask implements Runnable{
        @Override
        public void run() {
            try{
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
            onConnected();
        }
    }

    private class HearbeadTask extends TimerTask{
        @Override
        public void run() {
            if (!testConnection()){
                onDisconnected();
                reconnect();
            }
        }

        private boolean testConnection(){
            return true;
        }

        private void reconnect(){
            ConnectingTask connectingThread = new ConnectingTask();
            //直接在心跳定时器线程中执行
            connectingThread.run();
        }
    }

}
