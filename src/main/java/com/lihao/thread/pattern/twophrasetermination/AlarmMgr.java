package com.lihao.thread.pattern.twophrasetermination;


import com.lihao.thread.pattern.guardedsuspensionpattern.AlarmInfo;

/**
 * 告警功能入口类
 * 模式角色：Two-phraseTermination.ThreadOwner
 */
public class AlarmMgr {
    //AlarmMgr单例
    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile boolean shutdownRequested = false;
    //告警发送线程
    private final AlarmSendingThread alarmSendingThread;
    //私有构造器
    private AlarmMgr(){
        alarmSendingThread = new AlarmSendingThread();
    }
    //饿汉式单例，获取实例方法
    public static AlarmMgr getInstance(){
        return INSTANCE;
    }

    /**
     * 发送告警
     * @param type 告警类型
     * @param id 告警编号
     * @param extraInfo 告警参数
     * @return 由type+id+extraInfo唯一确定的告警信息被提交的次数。-1表示告警管理器已被关闭
     */
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
