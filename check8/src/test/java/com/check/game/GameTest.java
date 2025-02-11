package com.check.game;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;
import com.check.characters.Character;

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
    }

    @Test
    public void testStateTransition() {
        GameState newState = new InProgress();
        game.setState(newState);
        assertEquals("Game state should be updated", newState, game.getState());
    }

    @Test
    public void testRequest() {
        // Create a mock state that we can verify was called
        MockGameState mockState = new MockGameState();
        game.setState(mockState);
        game.request();
        assertTrue("Request should be handled by current state", mockState.wasHandleRequestCalled());
    }

    @Test
    public void testGameCache() {
        // Setup some game data
        GameCache cache = new GameCache();
        List<Character> characters = List.of(new Character(), new Character());
        cache.setCharacters(characters);
        cache.setRounds(5);

        // Test restore from cache
        game.restoreFromCache(cache);
        assertEquals("Characters should be restored", characters, game.getCharacters());
        assertEquals("Rounds should be restored", 5, game.getRounds());
    }

    // Helper mock class for testing state behavior
    private class MockGameState implements GameState {
        private boolean handleRequestCalled = false;

        @Override
        public void handleRequest(Game game) {
            handleRequestCalled = true;
        }

        public boolean wasHandleRequestCalled() {
            return handleRequestCalled;
        }
    }
} 