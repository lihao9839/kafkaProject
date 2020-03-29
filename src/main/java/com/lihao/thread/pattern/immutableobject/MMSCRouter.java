package com.lihao.thread.pattern.immutableobject;

import com.lihao.thread.pattern.threadspecificstorage.ManagedThreadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 彩信中心路由规则管理器
 * 模式角色：ImmutableObject.ImmutableObject
 */
public class MMSCRouter {
    private static volatile MMSCRouter instance = new MMSCRouter();
    private final Map<String, MMSCInfo> routerMap;

    public MMSCRouter(){
        this.routerMap = MMSCRouter.retrieveRouterMapFromDB();
    }

    private static Map<String, MMSCInfo> retrieveRouterMapFromDB(){
        Map<String, MMSCInfo> map = new HashMap<>();
        return map;
    }

    public static MMSCRouter getInstance(){
        return instance;
    }

    public MMSCInfo getMMSC(String msisdnPrefix){
        return routerMap.get(msisdnPrefix);
    }

    public static void setInstance(MMSCRouter newInstance){
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> map){
        Map<String, MMSCInfo> result = new HashMap<>();
        for(String key: map.keySet()){
            result.put(key, new MMSCInfo(map.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouterMap(){
        return Collections.unmodifiableMap(deepCopy(routerMap));
    }
}
