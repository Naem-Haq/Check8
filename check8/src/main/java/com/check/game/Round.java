package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.items.Inventory.InvalidCPUMoveException;

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
        try {
            if (player1Input == Controls.getDodge() && !player1.canDodge()) {
                logger.debug("Player 1 tries to dodge but has no dodges remaining");
                player1.setAttackable(true); // Ensure player1 is attackable
                player1Input = Controls.getAttack(); // Default to attack
            }
            if (player2Input == Controls.getDodge() && !player2.canDodge()) {
                logger.debug("Player 2 tries to dodge but has no dodges remaining");
                player2.setAttackable(true); // Ensure player2 is attackable
                player2Input = Controls.getAttack(); // Default to attack
            }

            if (player1Input == Controls.getDodge() ^ player2Input == Controls.getDodge()) {
                if (player1Input == Controls.getDodge()) {
                    player2Controls.pressButton(player2Input, player1);
                    logger.debug("Player 1 dodged in round {}", roundNumber);
                    player1.setAttackable(true); // Ensure player1 is attackable after dodge
                } else {
                    player1Controls.pressButton(player1Input, player2);
                    logger.debug("Player 2 dodged in round {}", roundNumber);
                    player2.setAttackable(true); // Ensure player2 is attackable after dodge
                }
            } else {
                player1Controls.pressButton(player1Input, player2);
                player2Controls.pressButton(player2Input, player1);
                logger.debug("Both players executed actions in round {}", roundNumber);
            }
        } catch (InvalidCPUMoveException e) {
            logger.error("Invalid CPU move: {}. Defaulting to attack.", e.getMessage());
            if (player1.isCPU()) {
                player1.setAttackable(true);
                System.out.println(player1.getName() + " defaults to attack");
                player1Controls.pressButton(Controls.getAttack(), player2);
            }
            if (player2.isCPU()) {
                player2.setAttackable(true);
                System.out.println(player2.getName() + " defaults to attack");
                player2Controls.pressButton(Controls.getAttack(), player1);
            }
        } catch (Exception e) {
            logger.error("Error executing action", e);
            player1.setAttackable(true); // Ensure player1 is attackable
            player2.setAttackable(true); // Ensure player2 is attackable
            System.out.println(player1.getName() + " and " + player2.getName() + " default to attack");
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