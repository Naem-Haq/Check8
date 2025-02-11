package com.check.game;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private List<Character> characters;
    private int rounds;
    private GameState state;

    public Game() {
        this.characters = new ArrayList<>();
        this.state = new Ready();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest(this);
    }

    public void saveToCache() {
        System.out.println("Game state saved.");
    }

    public void restoreFromCache(GameCache cache) {
        this.characters = cache.getCharacters();
        this.rounds = cache.getRounds();
        System.out.println("Game state restored.");
    }
}
