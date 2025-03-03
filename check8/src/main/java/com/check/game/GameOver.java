package com.check.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.check.login.User;

public class GameOver implements GameState {
    private static Logger logger = LoggerFactory.getLogger(GameOver.class.getName());

    @Override
    public String play(Game game) {
        updateStats(game);
        game.setState(new Ready());
        logger.info("Game over. Returning to Ready state");
        String result = String.format("Player 1 Health: %d, Player 2 Health: %d", game.getPlayer1().getHealthBar().getHealth(), game.getPlayer2().getHealthBar().getHealth());
        logger.info(result);
        return result;
    }
    
    @Override
    public Type getType() {
        return Type.GAME_OVER;
    }
    
    // @Override
    // public void update(int health) {
    //     if (health <= 0) {
    //         isGameOver = true;
    //         logger.debug("Game over triggered by health reaching 0");
    //     }
    // }
    
    private void updateStats(Game game) {
        User user = game.getCurrentUser();
        if (user != null) {
            if (game.getPlayer1().getHealthBar().getHealth() <= 0) {
                user.addLoss();
            } else {
                user.addWin();
            }
            user.saveStats();
        }
    }
} 