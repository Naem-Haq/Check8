package com.check.login;

import java.util.*;
import com.check.data.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class User {
    private String name;
    private String password;
    private int gamesPlayed = 0;
    private int gamesLost = 0;
    private int gamesWon = 0;

    private static final Logger logger = LoggerFactory.getLogger(User.class);


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

    public String getPassword() {
        return password;
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

        String currentUser = name;

        // Load all user stats from DataHandler
        Map<String, Map<String, Integer>> allStats = DataHandler.loadUserStats();

        // Check if the current user's stats exist
        if (allStats.containsKey(currentUser)) {
            stats = allStats.get(currentUser);
        } else {
            saveStats();
        }

        return stats;
    }

    public void saveStats() {
        DataHandler.saveUserStats(name, gamesPlayed, gamesLost, gamesWon);
    }

}
