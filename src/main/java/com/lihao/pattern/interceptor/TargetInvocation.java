package com.lihao.interceptor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TargetInvocation {

    private List<Interceptor> interceptorList = new ArrayList<>();
    private Iterator<Interceptor> interceptors;
    private Target target;
    private Request request;

    public Response invoke(){
        if(interceptors.hasNext()){
            Interceptor interceptor = interceptors.next();
            interceptor.intercept(this);
        }
        return target.execute(request);
    }

    public void addInterceptor(Interceptor interceptor){
        interceptorList.add(interceptor);
        interceptors = interceptorList.iterator();
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setTarget(Target target){
        this.target = target;
    }
}
