package com.check.game;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.characters.Character;
import com.check.data.GameCache;
import com.check.characters.CharacterCreator;

public class Game {
    private static Logger logger = LoggerFactory.getLogger(Game.class.getName());
    private List<Character> characters;
    private GameState state;
    private int rounds;
    private Character player;
    private Character cpu;

    public Game() {
        this.characters = new ArrayList<>();
        this.state = new Ready();
        this.rounds = 0;
        logger.info("New game initialized");
    }

    public void setState(GameState state) {
        this.state = state;
        logger.debug("Game state changed to: {}", state.getClass().getSimpleName());
    }

    public void request() {
        state.handleRequest(this);
    }

    public GameState getCurrentState() {
        return state;
    }

    public void initializeGame(String playerCharType, String cpuCharType) throws CharacterCreator.InvalidCharacterException {
        player = CharacterCreator.createCharacter(playerCharType, false);
        cpu = CharacterCreator.createCharacter(cpuCharType, true);
        characters.add(player);
        characters.add(cpu);
        setState(new InProgress());
        logger.info("Game initialized with {} vs {}", playerCharType, cpuCharType);
    }

    public Character getPlayer() {
        return player;
    }

    public Character getCPU() {
        return cpu;
    }

    public GameCache saveToCache() {
        return new GameCache(characters, state, rounds);
    }

    public void restoreFromCache(GameCache cache) {
        this.characters = new ArrayList<>(cache.getCharacters());
        this.state = cache.getState();
        this.rounds = cache.getRounds();
        logger.info("Game restored from cache");
    }

    public void incrementRounds() {
        rounds++;
    }

    public int getRounds() {
        return rounds;
    }
}
