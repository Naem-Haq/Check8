package com.check.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.check.characters.Character;

// Memento Design Pattern - Originator
public class GameData {
    private List<Character> players = new ArrayList<>(); // ArrayList to store characters

    // Set the state for a list of players
    public void setState(List<Character> players) {
        this.players = players;
    }

    // Save the state of all players
    public GameCache save() {
        Map<String, Object> state = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            state.put("player" + (i + 1) + "_health", players.get(i).getHealthBar().getHealth());
        }
        return new GameCache(state);
    }

    // Restore the state from the GameCache
    public void restore(GameCache gameCache) {
        Map<String, Object> state = gameCache.getState();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getHealthBar().setHealth((int) state.get("player" + (i + 1) + "_health"));
        }
    }

    // Print the state of each player
    public void printState() {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + (i + 1) + ": " + players.get(i).getName() + " | Health: " + players.get(i).getHealthBar().getHealth());
        }
    }

    // Add a player to the game
    public void addPlayer(Character player) {
        players.add(player);
    }
}