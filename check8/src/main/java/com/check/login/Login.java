package com.check.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    private static final String LOGIN_FILE = "check8/src/main/java/com/check/resources/AllUsers.csv";
    public static void signUp(String name, String password) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOGIN_FILE));
            for (String line : lines) {
                if (line.split(",")[0].equals(name)) {
                    System.out.println("User already exists.");
                    return;
                }
            }

            String hashedPassword = hashPassword(password);
            Files.write(Paths.get(LOGIN_FILE), (name + "," + hashedPassword + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("User registered successfully.");

            User newUser = new User(name, password);
            newUser.saveStats();
        } catch (IOException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "File operation error", e);
        }
    }

    public static User logIn(String name, String password) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOGIN_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(name) && parts[1].equals(hashPassword(password))) {
                    System.out.println("Login successful!");
                    return new User(name, password);
                }
            }
            System.out.println("Invalid username or password.");
        } catch (IOException e) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "File operation error", e);
        }
        return null;
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
