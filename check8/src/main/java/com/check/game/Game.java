package com.check.game;

import com.check.characters.Character;
import com.check.data.GameCache;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private List<Character> characters;
    private int rounds;
    private GameState state;

    public Game() {
        this.characters = new ArrayList<>();
        this.state = new Ready();
        this.rounds = 0;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void request() {
        state.handleRequest(this);
    }


    public void setCacheState(List<Character> characters, GameState state, int rounds) {
        this.characters = new ArrayList<>(characters);
        this.state = state;
        this.rounds = rounds;
    }
    public GameCache saveToCache() {
        System.out.println("Game state saved.");
        return new GameCache(getCharacters(),getState(), getRounds());
    }

    public void restoreFromCache(GameCache cache) {
        this.characters = cache.getCharacters();
        this.rounds = cache.getRounds();
        this.state = cache.getState();
        System.out.println("Game state restored.");
    }

    public GameState getState() {
        return state;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public int getRounds() {
        return rounds;
    }
}
