package com.check.login;

import com.check.data.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationInterceptor implements Interceptor{

    private static final Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class);
    @Override
    public void execute(Request request) {

        User user = request.getUser();

        if (user == null) {
            logger.error("Validation Failed: No user provided.");
            throw new SecurityException("Validation Failed: No user provided.");
        }

        String username = user.getName();
        String hashedPassword = user.getPassword();

        boolean isAuthenticated = DataHandler.loadUserLogin(username, hashedPassword);

        if (!isAuthenticated) {
            logger.error("Validation Failed: Incorrect username or password.");
            throw new SecurityException("Validation Failed: Incorrect username or password.");
        }
    }

}
