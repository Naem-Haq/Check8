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

    @Test
    public void testSaveAndRestore() {
        Game game = new Game();
        GameHistory history = new GameHistory();

        // Create initial characters
        Character player1 = new Knight(false);
        Character player2 = new Brute(true);
        player1.getHealthBar().setHealth(100);
        player2.getHealthBar().setHealth(100);

        // Create a list of players
        List<Character> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Set the initial state and save it
        game.setCacheState(players, game.getState(), 0);
        history.save(game);

        // Modify game state with new health values
        List<Character> modifiedPlayers = new ArrayList<>();
        Character modifiedPlayer1 = new Knight(false);
        Character modifiedPlayer2 = new Brute(true);
        modifiedPlayer1.getHealthBar().setHealth(30);
        modifiedPlayer2.getHealthBar().setHealth(50);
        modifiedPlayers.add(modifiedPlayer1);
        modifiedPlayers.add(modifiedPlayer2);

        game.setCacheState(modifiedPlayers, game.getState(), 100);
        history.save(game);

        // Further modify game state
        List<Character> furtherModifiedPlayers = new ArrayList<>();
        Character furtherModifiedPlayer1 = new Knight(false);
        Character furtherModifiedPlayer2 = new Brute(true);
        furtherModifiedPlayer1.getHealthBar().setHealth(20);
        furtherModifiedPlayer2.getHealthBar().setHealth(40);
        furtherModifiedPlayers.add(furtherModifiedPlayer1);
        furtherModifiedPlayers.add(furtherModifiedPlayer2);

        game.setCacheState(furtherModifiedPlayers, game.getState(), 200);

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
        assertEquals(100, game.getRounds());
    }
}
