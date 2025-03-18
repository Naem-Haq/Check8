package com.check.data;

import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.characters.Character;
import com.check.game.Game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

public class GameDataTest {

    @Test
    public void testSaveAndRestore() {
        // Create initial characters
        Character player1 = new Knight(false);
        Character player2 = new Brute(true);
        player1.getHealthBar().setHealth(100);
        player2.getHealthBar().setHealth(100);

        // Initialize the game with the characters
        Game game = new Game(player1, player2);
        GameHistory history = new GameHistory();

        // Save the initial state
        history.save(game);

        // Modify game state with new health values
        player1.getHealthBar().setHealth(30);
        player2.getHealthBar().setHealth(50);

        // Save the modified state
        history.save(game);

        // Further modify game state
        player1.getHealthBar().setHealth(20);
        player2.getHealthBar().setHealth(40);

        // Get the state before undo
        List<Character> beforeUndo = game.getCharacters();
        assertEquals(20, beforeUndo.get(0).getHealthBar().getHealth());
        assertEquals(40, beforeUndo.get(1).getHealthBar().getHealth());

        // Undo to the previous state
        history.undo(game);

        // Check the restored state
        List<Character> restoredCharacters = game.getCharacters();
        assertEquals(30, restoredCharacters.get(0).getHealthBar().getHealth());
        assertEquals(50, restoredCharacters.get(1).getHealthBar().getHealth());
        assertEquals(1, game.getNumRounds()); // Assuming numRounds is incremented in the game logic
    }
}