package com.check.game;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.check.characters.Character;
import com.check.characters.CharacterCreator;
import com.check.data.GameCache;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testInitialState() {
        assertNotNull("Game should be initialized", game);
        assertTrue("Initial state should be Ready", game.getState() instanceof Ready);
        assertEquals("Initial rounds should be 0", 0, game.getRounds());
    }

    @Test
    public void testInitializeGame() throws CharacterCreator.InvalidCharacterException {
        game.initializeGame("knight", "knight");
        assertNotNull("Player should be initialized", game.getPlayer());
        assertNotNull("CPU should be initialized", game.getCPU());
        assertTrue("Game should be in progress", game.getState() instanceof InProgress);
        assertEquals("Should have 2 characters", 2, game.getCharacters().size());
    }

    @Test(expected = CharacterCreator.InvalidCharacterException.class)
    public void testInitializeGameWithInvalidCharacter() throws CharacterCreator.InvalidCharacterException {
        game.initializeGame("invalid", "knight");
    }

    @Test
    public void testGameStateTransition() {
        game.setState(new InProgress());
        assertTrue("State should be InProgress", game.getState() instanceof InProgress);
        
        game.setState(new GameOver());
        assertTrue("State should be GameOver", game.getState() instanceof GameOver);
    }

    @Test
    public void testSaveAndRestoreFromCache() throws CharacterCreator.InvalidCharacterException {
        game.initializeGame("knight", "knight");
        int initialRounds = game.getRounds();
        GameState initialState = game.getState();
        
        GameCache cache = game.saveToCache();
        game.incrementRounds();
        game.setState(new GameOver());
        
        game.restoreFromCache(cache);
        assertEquals("Rounds should be restored", initialRounds, game.getRounds());
        assertEquals("State should be restored", initialState.getClass(), game.getState().getClass());
    }
} 