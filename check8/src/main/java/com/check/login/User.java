package com.check.login;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.JSONException;


public class User {
    private String name;
    private String password;
    private int gamesPlayed = 0;
    private int gamesLost = 0;
    private int gamesWon = 0;

    private static final String STATS_FILE = "check8/src/main/java/com/check/resources/UserStats.json";


    public User(String name, String password) {
        this.name = name;
        this.password = Login.hashPassword(password);
        this.gamesPlayed = 0;
        this.gamesLost = 0;
        this.gamesWon = 0;
        loadStats();
    }
    public String getName() {
        return name;
    }

    public void addWin() {
        gamesWon++;
        addGamePlayed();
    }
    public void addLoss() {
        gamesLost++;
        addGamePlayed();
    }
    public void addGamePlayed() {
        gamesPlayed++;
        saveStats();
    }

    public Map<String, Integer> loadStats() {
        Map<String, Integer> stats = new HashMap<>();
        Path statsFilePath = Paths.get(STATS_FILE);

        if (!Files.exists(statsFilePath)) return stats; // Return empty map if file doesn't exist

        try {
            String content = new String(Files.readAllBytes(statsFilePath));
            if (content.isEmpty()) return stats; // Return empty map if file is empty

            JSONObject json = new JSONObject(content);
            if (!json.has(name)) return stats; // Return empty map if user is not in the JSON

            JSONObject userStats = json.getJSONObject(name);
            stats.put("gamesPlayed", userStats.getInt("gamesPlayed"));
            stats.put("wins", userStats.getInt("wins"));
            stats.put("losses", userStats.getInt("losses"));

        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }

        return stats;
    }
    public void saveStats() {
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, "File operation error", e);
        }
    }


}
