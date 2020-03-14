package com.lihao.interceptor;

public class AuditInterceptor implements Interceptor {
    @Override
    public Response intercept(TargetInvocation targetInvocation) {
//        if(targetInvocation.getTarget() == null){
//            throw new IllegalArgumentException("Target is null");
//        }
        System.out.println("Audit Succeed");
        return targetInvocation.invoke();
    }
}
