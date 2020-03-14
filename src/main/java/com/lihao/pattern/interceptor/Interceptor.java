package com.lihao.interceptor;

public interface Interceptor {
    public Response intercept(TargetInvocation targetInvocation);
}
