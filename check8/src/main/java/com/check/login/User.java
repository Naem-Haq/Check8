package com.check.login;

import java.util.*;
import com.check.data.DataHandler;


public class User {
    private String name;
    private String password;
    private int gamesPlayed = 0;
    private int gamesLost = 0;
    private int gamesWon = 0;

    DataHandler dataHandler = new DataHandler();


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

        String currentUser = name;

        // Load all user stats from DataHandler
        Map<String, Map<String, Integer>> allStats = dataHandler.loadUserStats();

        // Check if the current user's stats exist
        if (allStats.containsKey(currentUser)) {
            stats = allStats.get(currentUser);
        } else {
            saveStats();
        }

        return stats;
    }

    public void saveStats() {
        dataHandler.saveUserStats(name, gamesPlayed, gamesLost, gamesWon);
    }

    public static void main(String[] args) {
        User michelle = new User("michelle", "pass");
        System.out.println(michelle.loadStats());
    }

}
