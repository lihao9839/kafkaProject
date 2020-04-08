package com.lihao.thread.pattern.guardedsuspensionpattern;

import com.lihao.thread.pattern.twophrasetermination.AlarmType;
import lombok.Data;

@Data
public class AlarmInfo {
    private String id;
    private AlarmType type;
    private String extraInfo;

    public AlarmInfo(String id, AlarmType type){
        this.id = id;
        this.type = type;
    }
    public AlarmInfo(String id, AlarmType type, String extraInfo){
        this.id = id;
        this.type = type;
        this.extraInfo = extraInfo;
    }
}
