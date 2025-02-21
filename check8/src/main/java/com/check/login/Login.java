package com.check.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.check.data.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    public static void signUp(String name, String password) {
        String hashedPassword = hashPassword(password);
        DataHandler.saveUserLogin(name, hashedPassword);
        User newUser = new User(name, password);
        newUser.loadStats();
        newUser.saveStats();
        logger.info("{} user signed up", DataHandler.maskUsername(name));
    }

    public static User logIn(String name, String password) {
        Request request = new Request(new User(name, password));

        InterceptorChain i = new InterceptorChain();
        i.addInterceptor(new LoggingInterceptor());
        i.addInterceptor(new ValidationInterceptor());

        try {
            i.execute(request);
            logger.info("Login succeeded for {}.", DataHandler.maskUsername(request.getUser().getName()));
            request.getUser().loadStats();
            return request.getUser();
        } catch (Exception e) {
            logger.error("Login failed for user {}. {}", DataHandler.maskUsername(request.getUser().getName()), e.getMessage());
            System.out.println("Login failed");
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

}
