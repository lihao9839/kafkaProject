package com.lihao.thread.pattern.immutableobject;

/**
 * 与运维中心 (Operation and Maintenance Center)对接的类
 * 模式角色：ImmutableObject.Manipulator
 */
public class OMCAgent extends Thread {
    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updatedTableName = null;
        while(true){

            if(isTableModificationMsg){
                if("MMSCInfo".equals(updatedTableName)){
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
        }
    }
}
