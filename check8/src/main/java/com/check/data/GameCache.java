package com.check.data;

import com.check.game.GameState;
import com.check.characters.Character;

// Memento Design Pattern - Memento
public class GameCache {
    private final Character player1;
    private final Character player2;
    private final GameState state;
    private final int rounds;
    private final int player1Health;
    private final int player2Health;

    public GameCache(Character player1, Character player2, GameState state, int rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.rounds = rounds;
        this.player1Health = player1.getHealthBar().getHealth();
        this.player2Health = player2.getHealthBar().getHealth();
    }

    public Character getPlayer1() {
        return player1;
    }

    public Character getPlayer2() {
        return player2;
    }

    public int getRounds() {
        return rounds;
    }

    public GameState getState() {
        return state;
    }

    public int getPlayer1Health() {
        return player1Health;
    }

    public int getPlayer2Health() {
        return player2Health;
    }
}
