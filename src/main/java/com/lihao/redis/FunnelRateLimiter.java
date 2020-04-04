package com.lihao.redis;

import java.util.HashMap;
import java.util.Map;

public class FunnelRateLimiter {
    int capacity;
    float leakingRate;
    int leftQuota;
    long leakingTs;

    public FunnelRateLimiter(int capacity, float leakingRate){
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = capacity;
        this.leakingTs = System.currentTimeMillis();
    }

    public void makeSpace(){
        long nowTs = System.currentTimeMillis();
        long deltaTs = nowTs - leakingTs;
        int deltaQuota = (int) (deltaTs * leakingRate);
        if(deltaQuota < 0){
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
            return;
        }
        if(deltaQuota < 1){
            return;
        }
        this.leftQuota += deltaQuota;
        this.leakingTs = nowTs;
        if(this.leftQuota > this.capacity){
            this.leftQuota = this.capacity;
        }
    }

    boolean watering(int quota){
        makeSpace();
        if(this.leftQuota >= quota){
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }

    private Map<String, FunnelRateLimiter> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate){
        String key = String.format("%s:%s",  userId, actionKey);
        FunnelRateLimiter funnel = funnels.get(key);
        if(funnel == null){
            funnel = new FunnelRateLimiter(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1);
    }
}
