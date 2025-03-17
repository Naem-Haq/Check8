package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Round {
    private static final Logger logger = LoggerFactory.getLogger(Round.class.getName());
    private final int roundNumber;
    private final Character player1;
    private final Character player2;
    private final Controls player1Controls;
    private final Controls player2Controls;
    private boolean complete;

    public Round(int roundNumber, Character player1, Character player2, Controls player1Controls, Controls player2Controls) {
        this.roundNumber = roundNumber;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Controls = player1Controls;
        this.player2Controls = player2Controls;
        this.complete = false;
        logger.debug("Round {} initialized", roundNumber);
    }

    public void executeAction(int player1Input, int player2Input) {
        // Guard for player1's move
        if (player1Input == Controls.getUseHealPotion() && !player1.getInventory().hasHealPotion()) {
            player1Input = Controls.getAttack();
        } else if (player1Input == Controls.getUseDamagePotion() && !player1.getInventory().hasDamagePotion()) {
            player1Input = Controls.getAttack();
        } else if (player1Input == Controls.getDodge() && !player1.canDodge()) {
            player1Input = Controls.getAttack();
        }

        // Guard for player2's move
        if (player2Input == Controls.getUseHealPotion() && !player2.getInventory().hasHealPotion()) {
            player2Input = Controls.getAttack();
        } else if (player2Input == Controls.getUseDamagePotion() && !player2.getInventory().hasDamagePotion()) {
            player2Input = Controls.getAttack();
        } else if (player2Input == Controls.getDodge() && !player2.canDodge()) {
            player2Input = Controls.getAttack();
        }

        try {
            if (player1Input == Controls.getDodge() ^ player2Input == Controls.getDodge()) {
                if (player1Input == Controls.getDodge()) {
                    player2Controls.pressButton(player2Input, player1);
                    logger.debug("Player 1 dodged in round {}", roundNumber);
                    player1.setAttackable(true);
                } else {
                    player1Controls.pressButton(player1Input, player2);
                    logger.debug("Player 2 dodged in round {}", roundNumber);
                    player2.setAttackable(true);
                }
            } else {
                player1Controls.pressButton(player1Input, player2);
                player2Controls.pressButton(player2Input, player1);
                logger.debug("Both players executed actions in round {}", roundNumber);
            }
        } catch (Exception e) {
            logger.error("Error executing action", e);
            player1.setAttackable(true);
            player2.setAttackable(true);
            player1Controls.pressButton(Controls.getAttack(), player2);
            player2Controls.pressButton(Controls.getAttack(), player1);
        }
        logger.debug("Player 1 Health: {}", player1.getHealthBar().getHealth());
        logger.debug("Player 2 Health: {}", player2.getHealthBar().getHealth());
        complete = true;
        logger.debug("Round {} completed", roundNumber);
    }

    public boolean isComplete() {
        return complete;
    }
}