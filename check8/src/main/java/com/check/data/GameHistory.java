package com.check.data;

import com.check.game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

//Memento Design Pattern - Caretaker
public class GameHistory {
    private static final Logger logger = LoggerFactory.getLogger(GameHistory.class);
    private final Stack<GameCache> history = new Stack<>();

    public void save(Game game) {
        history.push(game.saveToCache());
    }

    public void undo(Game game) {
        if (!history.isEmpty()) {
            game.restoreFromCache(history.pop());
            logger.info("Restored previous game state from Game history stack");
        } else {
            logger.info("No saves to restore in game history stack");
        }
    }
}

