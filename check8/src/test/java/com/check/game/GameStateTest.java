package com.check.game;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import com.check.characters.CharacterCreator;

public class GameStateTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testReadyState() {
        Ready readyState = new Ready();
        readyState.handleRequest(game);
        assertTrue("Game should transition to InProgress", game.getState() instanceof InProgress);
    }

    @Test
    public void testInProgressState() throws CharacterCreator.InvalidCharacterException {
        game.initializeGame("knight", "knight");
        InProgress inProgressState = new InProgress();
        
        // Test normal gameplay
        game.getPlayer().getHealthBar().setHealth(100);
        game.getCPU().getHealthBar().setHealth(100);
        inProgressState.handleRequest(game);
        assertTrue("Game should stay InProgress with full health", game.getState() instanceof InProgress);
        
        // Test game over condition
        game.getPlayer().getHealthBar().setHealth(0);
        inProgressState.handleRequest(game);
        assertTrue("Game should transition to GameOver when player dies", game.getState() instanceof GameOver);
    }

    @Test
    public void testGameOverState() throws CharacterCreator.InvalidCharacterException {
        // Initialize game first
        game.initializeGame("knight", "knight");
        
        GameOver gameOverState = new GameOver();
        gameOverState.handleRequest(game);
        assertTrue("Game should remain in GameOver state", game.getState() instanceof GameOver);
    }
} 