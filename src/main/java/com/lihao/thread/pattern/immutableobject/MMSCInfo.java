package com.lihao.thread.pattern.immutableobject;

import lombok.Data;
import lombok.Getter;

/**
 * 彩信信息中心
 */
@Getter
public final class MMSCInfo {

    private final String derviceID;
    private final String url;
    private final int maxAttachementSizeInBytes;

    public MMSCInfo(String deviceID, String url, int maxAttachementSizeInBytes){
        this.derviceID = deviceID;
        this.url = url;
        this.maxAttachementSizeInBytes = maxAttachementSizeInBytes;
    }

    public MMSCInfo(MMSCInfo prototype){
        this.derviceID = prototype.derviceID;
        this.url = prototype.url;
        this.maxAttachementSizeInBytes = prototype.maxAttachementSizeInBytes;
    }
}
