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
        logger.info("Game win added to {}", getName());
    }
    public void addLoss() {
        gamesLost++;
        addGamePlayed();
        logger.info("Game loss added to {}", getName());
    }
    public void addGamePlayed() {
        gamesPlayed++;
        saveStats();
        logger.info("total games played increased to {} but User {}", gamesPlayed, getName());
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

        logger.info("Game stats for User {} loaded.", DataHandler.maskUsername(getName()));
        return stats;
    }

    public void saveStats() {
        DataHandler.saveUserStats(name, gamesPlayed, gamesLost, gamesWon);
        logger.info("Game stats for user {} saved", DataHandler.maskUsername(getName()));
    }

}
