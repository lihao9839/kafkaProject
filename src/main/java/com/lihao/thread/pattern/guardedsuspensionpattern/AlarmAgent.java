package com.lihao.thread.pattern.guardedsuspensionpattern;

/**
 * 负责连接告警服务，并发送告警信息至服务器
 */
public class AlarmAgent {
    private volatile boolean connectedToServer = false;
}
