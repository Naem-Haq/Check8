package com.check.game;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.characters.Character;
import com.check.characters.HealthObserver;
import com.check.data.GameCache;
import com.check.characters.CharacterCreator;
import com.check.characters.CharacterCreator.InvalidCharacterException;
import com.check.characters.Controls;
import com.check.data.GameHistory;
import com.check.login.User;
import java.util.Arrays;

public class Game implements HealthObserver{
    private static final Logger logger = LoggerFactory.getLogger(Game.class.getName());
    
    // Core game components
    private GameState state;
    private Character player1;
    private Character player2;
    private Controls player1Controls;
    private Controls player2Controls;
    private User currentUser;
    
    // Game status
    private int numRounds;  
    private final GameHistory gameHistory;


    // Public constructor
    public Game(Character player1, Character player2) throws InvalidCharacterException {
        this.state = new Ready();
        this.gameHistory = new GameHistory();
        this.numRounds = 0; 
        this.player1 = player1;
        this.player2 = player2;
        this.player1Controls = new Controls(this.player1);
        this.player2Controls = new Controls(this.player2);
        this.player1.getHealthBar().attach(this);
        this.player2.getHealthBar().attach(this);
        this.player1.populateInventory();
        this.player2.populateInventory();
        logger.info("Game initialized with {} vs {}", player1, player2);
    }

    // Method to get a new instance of Game
    public static Game getInstance(Character player1, Character player2) throws InvalidCharacterException {
        return new Game(player1, player2);
    }

    public void start() {
        while (state.getType() != GameState.Type.GAME_OVER) {
            state.play(this);
        }
    }

    public void setState(GameState state) {
        this.state = state;
        logger.debug("Game state changed to: {}", state.getClass().getSimpleName());
    }

    public void incrementRounds() {
        numRounds++;
        logger.debug("Round incremented to: {}", numRounds);
    }

    // Memento pattern methods
    public GameCache saveToCache() {
        return new GameCache(getCharacters(), getState(), getNumRounds());
    }

    public void restoreFromCache(GameCache cache) {
        List<Character> characters = cache.getCharacters();
        this.player1 = characters.get(0);
        this.player2 = characters.get(1);
        this.state = cache.getState();
        this.numRounds = cache.getRounds(); // Ensure numRounds is restored
        logger.info("Game restored from cache");
    }

    // Getters
    public Character getPlayer1() { return player1; }
    public Character getPlayer2() { return player2; }
    public Controls getPlayer1Controls() { return player1Controls; }
    public Controls getPlayer2Controls() { return player2Controls; }
    public GameHistory getGameHistory() { return gameHistory; }
    public User getCurrentUser() { return currentUser; }
    public int getNumRounds() { return numRounds; }
    public GameState getState() { return state; }

    // Setters
    public void setPlayer1(Character player1) { 
        this.player1 = player1; 
        this.player1Controls = new Controls(player1);
    }
    public void setPlayer2(Character player2) { 
        this.player2 = player2;
        this.player2Controls = new Controls(player2);
    }
    public void setCurrentUser(User user) { 
        this.currentUser = user;
        logger.info("Current user set to: {}", user != null ? user.getName() : "null");
    }

    public List<Character> getCharacters() {
        return new ArrayList<>(Arrays.asList(player1, player2));
    }

    public void setCacheState(List<Character> characters, GameState state, int roundNumber) {
        this.player1 = characters.get(0);
        this.player2 = characters.get(1);
        this.state = state;
        logger.debug("Game state manually set with {} characters, state: {}", 
            characters.size(), state.getClass().getSimpleName());
    }

    public void endGame() {
        setState(new GameOver());
    }

    public void returnToReady() {
        setState(new Ready());
    }

    public String display() {
        return state.play(this);
    }

    public String getWinnerName() {
        if (state.getType() != GameState.Type.GAME_OVER) {
            return null;
        }
        return player1.getHealthBar().getHealth() <= 0 ? player2.getName() : player1.getName();
    }

    @Override
    public void update(int health) {
        logger.debug("Health update received: {}", health);
        if (health <= 0) {
            endGame();
        }
    }

    public void newRound(int player1Input, int player2Input) {
        Round round = new Round(numRounds, player1, player2, player1Controls, player2Controls);
        round.executeAction(player1Input, player2Input);
        if (round.isComplete()) {
            incrementRounds();
        }
        logger.debug("Player 1 Health after round: {}", player1.getHealthBar().getHealth());
        logger.debug("Player 2 Health after round: {}", player2.getHealthBar().getHealth());
        // Add debug logs to confirm health updates
        logger.info("Player 1 health after round: {}", player1.getHealthBar().getHealth());
        logger.info("Player 2 health after round: {}", player2.getHealthBar().getHealth());
        if (player1.getHealthBar().getHealth() <= 0 || player2.getHealthBar().getHealth() <= 0) {
            state = new GameOver();
            logger.info("Game over. Returning to Ready state");
        }
    }
}
