package com.check.data;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);

    private static final String LOGIN_FILE = "src/main/java/com/check/resources/AllUsers.csv";
    private static final String STATS_FILE = "src/main/java/com/check/resources/UserStats.json";

    private DataHandler() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static void saveUserStats(String name, int gamesPlayed, int gamesLost, int gamesWon){
        try {
            JSONObject json = new JSONObject();
            Path statsFilePath = Paths.get(STATS_FILE);

            // Check if the file exists and load the existing content if it does
            if (Files.exists(statsFilePath)) {
                String content = new String(Files.readAllBytes(statsFilePath));
                json = new JSONObject(content);  // Load the existing JSON
            }

            // Create or update the user stats
            JSONObject userStats = new JSONObject();
            userStats.put("username", name);
            userStats.put("gamesPlayed", gamesPlayed);
            userStats.put("wins", gamesWon);
            userStats.put("losses", gamesLost);

            // Update the JSON with user stats under their username as the key
            json.put(name, userStats);

            // Write back the JSON to the file with formatting
            Files.write(statsFilePath, json.toString(4).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException | JSONException e) {
            logger.error("File operation error", e);
        }
    }
    public static Map<String, Map<String, Integer>> loadUserStats() {
        Map<String, Map<String, Integer>> allStats = new HashMap<>();
        Path statsFilePath = Paths.get(STATS_FILE);

        if (!Files.exists(statsFilePath)) return allStats; // Return empty map if file doesn't exist

        try {
            String content = new String(Files.readAllBytes(statsFilePath));
            if (content.isEmpty()) return allStats; // Return empty map if file is empty

            JSONObject json = new JSONObject(content); // Load entire JSON file

            for (String user : json.keySet()) { // Iterate over all users
                JSONObject userStats = json.getJSONObject(user);
                Map<String, Integer> stats = new HashMap<>();

                stats.put("gamesPlayed", userStats.getInt("gamesPlayed"));
                stats.put("wins", userStats.getInt("wins"));
                stats.put("losses", userStats.getInt("losses"));

                allStats.put(user, stats);
            }

        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }

        return allStats;
    }

    public static boolean loadUserLogin(String name, String hashPassword){
        final int EXPECTED_PARTS = 2;
        final int USERNAME_INDEX = 0;
        final int PASSWORD_INDEX = 1;
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOGIN_FILE));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == EXPECTED_PARTS && parts[USERNAME_INDEX].equals(name) && parts[PASSWORD_INDEX].equals(hashPassword)) {
                    System.out.println("Login successful!");
                    return true;
                }
            }
            System.out.println("Invalid username or password.");
        } catch (IOException e) {
            logger.error("File operation error", e);
        }
        return false;
    }
    public static void saveUserLogin(String name, String hashedPassword){
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOGIN_FILE));
            for (String line : lines) {
                if (line.split(",")[0].equals(name)) {
                    logger.warn("User '{}' already exists.", name);
                    System.out.println("User already exists.");
                    return;
                }
            }

            Files.write(Paths.get(LOGIN_FILE), (name + "," + hashedPassword + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            logger.info("User registered successfully.");

        } catch (IOException e) {
            logger.error("File operation error", e);
        }
    }

}
