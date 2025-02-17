package com.check.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingInterceptor implements Interceptor{
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public void execute(Request request) {
        User user = request.getUser();
        logger.info("User {} attempted login.", user.getName());
    }
}
