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

    public String executeAction(int player1Input, int player2Input) {
        String p1Output;
        String p2Output;
        String totalOutput;
        if (player1Input == Controls.getDodge() ^ player2Input == Controls.getDodge()) {
            if (player1Input == Controls.getDodge()) {
                p1Output = player1Controls.pressButton(player1Input, player2);
                p2Output = player2Controls.pressButton(player2Input, player1);
                totalOutput = p1Output + "\n" + p2Output;
                logger.debug("Player 1 dodged in round {}", roundNumber);
                player1.setAttackable(true);
                player2.setAttackable(true);
            } else {
                p2Output = player2Controls.pressButton(player2Input, player1);
                p1Output = player1Controls.pressButton(player1Input, player2);
                totalOutput = p2Output + "\n" + p1Output;
                logger.debug("Player 2 dodged in round {}", roundNumber);
                player1.setAttackable(true);
                player2.setAttackable(true);
            }
        } else {
            p1Output = player1Controls.pressButton(player1Input, player2);
            p2Output = player2Controls.pressButton(player2Input, player1);
            totalOutput = p1Output + "\n" + p2Output;
            logger.debug("Both players executed actions in round {}", roundNumber);
            player1.setAttackable(true);
            player2.setAttackable(true);
        }
        logger.debug("Player 1 Health: {}", player1.getHealthBar().getHealth());
        logger.debug("Player 2 Health: {}", player2.getHealthBar().getHealth());
        complete = true;
        logger.debug("Round {} completed", roundNumber);
        return totalOutput;
    }

    public boolean isComplete() {
        return complete;
    }
}