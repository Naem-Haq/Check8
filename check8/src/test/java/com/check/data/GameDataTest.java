package com.check.data;

import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.characters.Character;
import com.check.game.Game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class GameDataTest {
    private static final int INITIAL_HEALTH = 100;
    private static final int SECOND_STATE_HEALTH_P1 = 30;
    private static final int SECOND_STATE_HEALTH_P2 = 50;
    private static final int THIRD_STATE_HEALTH_P1 = 20;
    private static final int THIRD_STATE_HEALTH_P2 = 40;
    
    @Test
    public void testInitialSave() {
        Game game = new Game();
        GameHistory history = new GameHistory();
        List<Character> players = createPlayers(INITIAL_HEALTH, INITIAL_HEALTH);
        
        game.setCacheState(players, game.getState(), 0);
        history.save(game);
        
        List<Character> savedState = game.getCharacters();
        assertEquals(INITIAL_HEALTH, savedState.get(0).getHealthBar().getHealth());
        assertEquals(INITIAL_HEALTH, savedState.get(1).getHealthBar().getHealth());
    }
    
    @Test
    public void testUndo() {
        Game game = new Game();
        GameHistory history = new GameHistory();
        
        // Initial state
        game.setCacheState(createPlayers(INITIAL_HEALTH, INITIAL_HEALTH), game.getState(), 0);
        history.save(game);
        
        // Second state
        game.setCacheState(createPlayers(SECOND_STATE_HEALTH_P1, SECOND_STATE_HEALTH_P2), game.getState(), 100);
        history.save(game);
        
        // Third state
        game.setCacheState(createPlayers(THIRD_STATE_HEALTH_P1, THIRD_STATE_HEALTH_P2), game.getState(), 200);
        
        // Verify current state before undo
        List<Character> beforeUndo = game.getCharacters();
        assertEquals(THIRD_STATE_HEALTH_P1, beforeUndo.get(0).getHealthBar().getHealth());
        assertEquals(THIRD_STATE_HEALTH_P2, beforeUndo.get(1).getHealthBar().getHealth());
        
        // Undo and verify
        history.undo(game);
        List<Character> restoredCharacters = game.getCharacters();
        assertEquals(SECOND_STATE_HEALTH_P1, restoredCharacters.get(0).getHealthBar().getHealth());
        assertEquals(SECOND_STATE_HEALTH_P2, restoredCharacters.get(1).getHealthBar().getHealth());
        assertEquals(100, game.getRounds());
    }
    
    private List<Character> createPlayers(int player1Health, int player2Health) {
        Character player1 = new Knight(false);
        Character player2 = new Brute(true);
        player1.getHealthBar().setHealth(player1Health);
        player2.getHealthBar().setHealth(player2Health);
        
        List<Character> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        return players;
    }
}
