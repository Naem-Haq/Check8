package com.check.data;

import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.characters.Character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class GameDataTest {

    @Test
    public void testSaveAndRestore() {
        GameData gameData = new GameData();
        GameHistory history = new GameHistory();

        // Create characters
        Character player1 = new Knight(false);
        Character player2 = new Brute(true);
        player1.getHealthBar().setHealth(100);
        player2.getHealthBar().setHealth(100);

        // Create a list of players and set the game state
        List<Character> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        gameData.setState(players);
        history.save(gameData);

        // Modify health
        player1.getHealthBar().setHealth(30);
        player2.getHealthBar().setHealth(50);

        gameData.setState(players);
        history.save(gameData);

        // Further modify health
        player1.getHealthBar().setHealth(20);
        player2.getHealthBar().setHealth(40);

        // Undo to the previous state
        history.undo(gameData);

        // Check that the health has been restored correctly
        assertEquals(30, player1.getHealthBar().getHealth());
        assertEquals(50, player2.getHealthBar().getHealth());
    }
}
