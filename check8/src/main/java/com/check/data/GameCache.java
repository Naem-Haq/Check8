package com.check.data;

import com.check.game.GameState;
import com.check.characters.Character;

//Memento Design Pattern - Memento
public class GameCache{
    private final Character player1;
    private final Character player2;
    private final GameState state;
    private final int rounds;

    public GameCache(Character player1, Character player2, GameState state, int rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.state = state;
        this.rounds = rounds;
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

}
