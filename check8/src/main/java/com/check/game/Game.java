package com.check.game;

import com.check.characters.Character;
import com.check.data.GameCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);
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
        logger.info("Game state saved onto stack");
        return new GameCache(getCharacters(),getState(), getRounds());
    }

    public void restoreFromCache(GameCache cache) {
        this.characters = cache.getCharacters();
        this.rounds = cache.getRounds();
        this.state = cache.getState();
        logger.info("Game state restored from stack");
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

    public void start() {
        // Initialize game state and start the game
        state = new Ready(); // Using Ready state instead of GameState
        // Add any other initialization logic
    }
}
