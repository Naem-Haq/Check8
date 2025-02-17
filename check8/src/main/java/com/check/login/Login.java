package com.check.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.check.data.DataHandler;

public class Login {

    public static void signUp(String name, String password) {
        String hashedPassword = hashPassword(password);
        DataHandler.saveUserLogin(name, hashedPassword);
        User newUser = new User(name, password);
        newUser.saveStats();
    }

    public static User logIn(String name, String password) {
        Request request = new Request(new User(name, password));

        InterceptorChain i = new InterceptorChain();
        i.addInterceptor(new LoggingInterceptor());
        i.addInterceptor(new AuthenticationInterceptor());

        try {
            i.execute(request);
            return request.getUser();
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }


    public static void main(String[] args) {
        Login.signUp("mich", "pass");
        User user = logIn("mich", "pass");
    }
}
