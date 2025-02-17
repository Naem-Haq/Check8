package com.check.login;

import com.check.data.DataHandler;

public class AuthenticationInterceptor implements Interceptor{
    @Override
    public void execute(Request request) {

        User user = request.getUser();

        if (user == null) {
            throw new SecurityException("Authentication Failed: No user provided.");
        }

        String username = user.getName();
        String hashedPassword = user.getPassword();

        boolean isAuthenticated = DataHandler.loadUserLogin(username, hashedPassword);

        if (!isAuthenticated) {
            throw new SecurityException("Authentication Failed: Incorrect username or password.");
        }
    }

}
