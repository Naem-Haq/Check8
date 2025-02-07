package com.check.data;

import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.characters.Character;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameDataTest {

    @Test
    public void testSaveAndRestore() {
        GameData gameData = new GameData();
        GameHistory history = new GameHistory();

        Character player1 = new Knight(false);
        Character player2 = new Brute(true);
        player1.getHealthBar().setHealth(100);
        player2.getHealthBar().setHealth(100);

        gameData.setState(player1, player2);
        history.save(gameData);

        player1.getHealthBar().setHealth(30);
        player2.getHealthBar().setHealth(50);

        gameData.setState(player1, player2);
        history.save(gameData);

        player1.getHealthBar().setHealth(20);
        player2.getHealthBar().setHealth(40);

        history.undo(gameData);
        assertEquals(30, player1.getHealthBar().getHealth());
        assertEquals(50, player2.getHealthBar().getHealth()); 
    }
}
