package com.check.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.check.characters.Character;
import com.check.characters.Knight;
import com.check.characters.Brute;
import com.check.characters.Controls;

public class RoundTest {
    private Round round;
    private Character player1;
    private Character player2;
    private Controls player1Controls;
    private Controls player2Controls;

    @Before
    public void setUp() {
        player1 = new Knight(false);
        player2 = new Brute(false);
        player1Controls = new Controls(player1);
        player2Controls = new Controls(player2);
        round = new Round(1, player1, player2, player1Controls, player2Controls);
    }

    @Test
    public void testExecuteAction() {
        round.executeAction(Controls.getAttack(), Controls.getDodge());
        assertTrue("Round should be complete", round.isComplete());
    }

    @Test
    public void testDodgeWithoutDodges() {
        player1.useDodge();
        player1.useDodge();
        player1.useDodge();
        round.executeAction(Controls.getDodge(), Controls.getAttack());
        assertEquals("Player1 should default to attack", Controls.getAttack(), Controls.getAttack());
    }

    @Test
    public void testBothPlayersAttack() {
        round.executeAction(Controls.getAttack(), Controls.getAttack());
        assertTrue("Round should be complete", round.isComplete());
    }
}
