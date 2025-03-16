package com.check.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.check.characters.Character;
import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.login.User;
import com.check.characters.Controls;
import com.check.data.GameCache;

public class GameTest {
    private Game game;
    private Character player1;
    private Character player2;
    private User user;

    @Before
    public void setUp() throws Exception {
        player1 = new Knight(false);
        player2 = new Brute(true);
        user = new User("testUser", "testPass");
        game = new Game(player1, player2);
        game.setCurrentUser(user);
    }

    @Test
    public void testInitialState() {
        assertEquals("Initial state should be Ready", GameState.Type.READY, game.getState().getType());
    }

    @Test
    public void testStateTransitionToInProgress() {
        game.setState(new InProgress());
        assertEquals("State should transition to InProgress", GameState.Type.IN_PROGRESS, game.getState().getType());
    }

    @Test
    public void testStateTransitionToGameOver() {
        game.setState(new GameOver());
        assertEquals("State should transition to GameOver", GameState.Type.GAME_OVER, game.getState().getType());
    }

    @Test
    public void testRoundExecution() {
        game.setState(new InProgress());
        game.newRound(Controls.getAttack(), Controls.getAttack());
        assertEquals("Round should be incremented", 1, game.getNumRounds());
    }

    @Test
    public void testHealthUpdate() {
        player1.getHealthBar().decreaseHealth(50);
        assertEquals("Player1 health should be 50", 50, player1.getHealthBar().getHealth());
    }

    @Test
    public void testGameOverState() {
        player1.getHealthBar().setHealth(0);
        game.update(player1.getHealthBar().getHealth());
        assertEquals("Game should be in GameOver state", GameState.Type.GAME_OVER, game.getState().getType());
    }

    @Test
    public void testSaveAndRestoreGameState() {
        game.setState(new InProgress());
        game.newRound(Controls.getAttack(), Controls.getAttack());
        GameCache cache = game.saveToCache();

        game.setState(new Ready());
        game.restoreFromCache(cache);

        assertEquals("State should be restored to InProgress", GameState.Type.IN_PROGRESS, game.getState().getType());
        assertEquals("Round number should be restored", 1, game.getNumRounds());
    }
}