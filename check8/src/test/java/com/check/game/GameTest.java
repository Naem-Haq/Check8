package com.check.game;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import com.check.characters.Character;
import com.check.characters.Knight;
import com.check.characters.Controls;
import com.check.data.GameCache;

public class GameTest {
    private Game game;
    private Character player1;
    private Character player2;

    @Before
    public void setUp() {
        player1 = new Knight(false);
        player2 = new Knight(true);
        game = new Game(player1, player2);
    }

    @Test
    public void testInitialState() {
        assertNotNull("Game should be initialized", game);
        assertTrue("Initial state should be Ready", game.getState() instanceof Ready);
    }

    @Test
    public void testStateTransition() {
        GameState newState = new InProgress();
        game.setState(newState);
        assertEquals("Game state should be updated", newState, game.getState());
    }

    @Test
    public void testNewRound() {
        int player1Move = Controls.getAttack();
        int player2Move = Controls.getDodge();
        String result = game.newRound(player1Move, player2Move);
        assertNotNull("Round result should not be null", result);
        assertEquals("Number of rounds should be incremented", 1, game.getNumRounds());
    }

    @Test
    public void testUpdateHealth() {
        player1.getHealthBar().setHealth(0);
        game.update(player1.getHealthBar().getHealth());
        assertTrue("Game state should be GameOver", game.getState() instanceof GameOver);
    }

    @Test
    public void testSaveAndRestoreFromCache() {
        player1.getHealthBar().setHealth(50);
        player2.getHealthBar().setHealth(75);
        GameCache cache = game.saveToCache();
        
        // Modify the game state
        game.setState(new GameOver());

        // Restore the game state
        game.restoreFromCache(cache);

        // Verify the restoration
        assertEquals("Player 1 health should be restored", 50, player1.getHealthBar().getHealth());
        assertEquals("Player 2 health should be restored", 75, player2.getHealthBar().getHealth());
        assertTrue("Game state should be Ready", game.getState() instanceof Ready);
    }

    @Test
    public void testGetWinnerName() {
        player1.getHealthBar().setHealth(0);
        game.update(player1.getHealthBar().getHealth());
        assertEquals("Winner should be player2", player2.getName(), game.getWinnerName());
    }
}