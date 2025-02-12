package com.check.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import com.check.data.DataHandler;

public class Login {

    static DataHandler dataHandler = new DataHandler();

    public static void signUp(String name, String password) {
        String hashedPassword = hashPassword(password);
        dataHandler.saveUserLogin(name, hashedPassword);
        User newUser = new User(name, password);
        newUser.saveStats();
    }

    public static User logIn(String name, String password) {
        boolean successfulLogin =  dataHandler.loadUserLogin(name, hashPassword(password));
        if (successfulLogin) return new User(name, password);
        else return null;
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
