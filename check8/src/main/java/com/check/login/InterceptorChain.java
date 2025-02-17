package com.check.login;

import java.util.ArrayList;

public class InterceptorChain {
    ArrayList<Interceptor> interceptors = new ArrayList<>();

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
    public void execute(Request request) throws Exception {
        for (Interceptor interceptor : interceptors) {
            interceptor.execute(request);
        }
    }
}
