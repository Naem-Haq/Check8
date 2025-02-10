package com.check.game;

public class GameMemento {
    private final int rounds;
    private final GameState state;
    
    public GameMemento(int rounds, GameState state) {
        this.rounds = rounds;
        this.state = state;
    }
    
    public int getRounds() { return rounds; }
    public GameState getState() { return state; }
}
