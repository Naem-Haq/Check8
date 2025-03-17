package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.login.User;

public class GameOver implements GameState {
    private static Logger logger = LoggerFactory.getLogger(GameOver.class.getName());

    @Override
    public String play(Game game) {
        updateStats(game);
        logger.info("Game over. Returning to Ready state");
        game.setState(new Ready());

        String result = String.format("Player 1 Health: %d, Player 2 Health: %d", game.getPlayer1().getHealthBar().getHealth(), game.getPlayer2().getHealthBar().getHealth());
        logger.info(result);
        return result;
    }
    
    @Override
    public Type getType() {
        return Type.GAME_OVER;
    }
    
    private void updateStats(Game game) {
        User user = game.getCurrentUser();
        if (user != null) {
            int player1Health = game.getPlayer1().getHealthBar().getHealth();
            int player2Health = game.getPlayer2().getHealthBar().getHealth();
            
            if (player1Health <= 0 && player2Health <= 0) {
                user.addTie();
            } else if (player1Health <= 0) {
                user.addLoss();
            } else if (player2Health <= 0) {
                user.addWin();
            } else {
                user.addTie();
            }
            user.saveStats();
        }
    }
} 