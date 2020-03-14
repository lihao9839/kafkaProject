package com.lihao.nettymessage;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

@Data
public class Header implements Serializable {
    private int crcCode;
    private int length;
    private long sessionId;
    private TrayIcon.MessageType type;
    private Map<String, Object> attachment;
}
