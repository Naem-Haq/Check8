package com.check.data;

import java.util.HashMap;
import java.util.Map;

import com.check.characters.Character;


//Memento Design Pattern - Originator
public class GameData {
    private Character player1;
    private Character player2;
    public void setState(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    //will update with portions once class is implemented
    public GameCache save() {
        Map<String, Object> state = new HashMap<>();
        state.put("player1_health", player1.getHealthBar().getHealth());
        state.put("player2_health", player2.getHealthBar().getHealth());
        return new GameCache(state);
    }
    public void restore(GameCache gameCache) {
        Map<String, Object> state = gameCache.getState();
        player1.getHealthBar().setHealth((int) state.get("player1_health"));
        player2.getHealthBar().setHealth((int) state.get("player2_health"));
    }

    public void printState() {
        System.out.println("Player 1: " + player1.getName() + " | Health: " + player1.getHealthBar().getHealth());
        System.out.println("Player 2: " + player2.getName() + " | Health: " + player2.getHealthBar().getHealth());
    }

    
}
