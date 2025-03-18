package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import com.check.characters.HealthBar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RoundTest {

    private Round round;
    private Character player1;
    private Character player2;
    private Controls player1Controls;
    private Controls player2Controls;
    private HealthBar player1HealthBar;
    private HealthBar player2HealthBar;

    @BeforeEach
    void setUp() {
        player1 = mock(Character.class);
        player2 = mock(Character.class);
        player1Controls = mock(Controls.class);
        player2Controls = mock(Controls.class);
        player1HealthBar = mock(HealthBar.class);
        player2HealthBar = mock(HealthBar.class);

        when(player1.getHealthBar()).thenReturn(player1HealthBar);
        when(player2.getHealthBar()).thenReturn(player2HealthBar);

        round = new Round(1, player1, player2, player1Controls, player2Controls);
    }

    @Test
     void testRoundInitialization() {
        assertNotNull(round);
        assertFalse(round.isComplete());
    }

    @Test
     void testExecuteActionBothPlayersAttack() {
        when(player1Controls.pressButton(anyInt(), eq(player2))).thenReturn("Player 1 attacks");
        when(player2Controls.pressButton(anyInt(), eq(player1))).thenReturn("Player 2 attacks");

        String result = round.executeAction(1, 1);

        assertEquals("Player 1 attacks\nPlayer 2 attacks", result);
        assertTrue(round.isComplete());
    }

    @Test
     void testExecuteActionPlayer1Dodges() {
        when(player1Controls.pressButton(anyInt(), eq(player2))).thenReturn("Player 1 dodges");
        when(player2Controls.pressButton(anyInt(), eq(player1))).thenReturn("Player 2 attacks");

        String result = round.executeAction(Controls.getDodge(), 1);

        assertEquals("Player 1 dodges\nPlayer 2 attacks", result);
        assertTrue(round.isComplete());
    }

    @Test
 void testExecuteActionPlayer2Dodges() {
    when(player1Controls.pressButton(anyInt(), eq(player2))).thenReturn("Player 1 attacks");
    when(player2Controls.pressButton(anyInt(), eq(player1))).thenReturn("Player 2 dodges");

    String result = round.executeAction(1, Controls.getDodge());

    assertEquals("Player 1 attacks\nPlayer 2 dodges", result);
    assertTrue(round.isComplete());
}
}