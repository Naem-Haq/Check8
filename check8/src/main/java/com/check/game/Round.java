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
        if (player1Input == Controls.getDodge() ^ player2Input == Controls.getDodge()) {
            if (player1Input == Controls.getDodge()) {
            player2Controls.pressButton(player2Input, player1);
            player1Controls.pressButton(player1Input, player2);
            logger.debug("Player 1 dodged in round {}", roundNumber);
            } else {
            player1Controls.pressButton(player1Input, player2);
            player2Controls.pressButton(player2Input, player1);
            logger.debug("Player 2 dodged in round {}", roundNumber);
            }
        } else {
            player1Controls.pressButton(player1Input, player2);
            player2Controls.pressButton(player2Input, player1);
            logger.debug("Both players executed actions in round {}", roundNumber);
        }
        complete = true;
        logger.debug("Round {} completed", roundNumber);
    }

    public boolean isComplete() {
        return complete;
    }
} 