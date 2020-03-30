package com.lihao.thread.pattern.twophrasetermination;

/**
 * 告警功能入口类
 */
public class AlarmMgr {

    private static final AlarmMgr INSTANCE = new AlarmMgr();
    private volatile boolean shutdownRequested = false;
    private final AlarmSendingThread alarmSendingThread;

    private AlarmMgr(){
        alarmSendingThread = new AlarmSendingThread();
    }

    public static AlarmMgr getInstance(){
        return INSTANCE;
    }

    public int sendAlarm(AlarmType type, String id, String extraInfo){
        int duplicateSubmissionCount = 0;
        try{
            AlarmInfo alarmInfo = new AlarmInfo(id, type);
            alarmInfo.setExtraInfo(extraInfo);
            duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        }catch (Throwable t){
            t.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    public void init(){
        alarmSendingThread.start();
    }

    public synchronized void shutdown(){
        if(shutdownRequested){
            throw new IllegalStateException("shutdow already requested!");
        }
        alarmSendingThread.terminate();
        shutdownRequested = true;
    }
}
