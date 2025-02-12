package com.check.data;

import com.check.game.Game;

import java.util.Stack;

//Memento Design Pattern - Caretaker
public class GameHistory {
    private final Stack<GameCache> history = new Stack<>();

    public void save(Game game) {
        history.push(game.saveToCache());
    }

    public void undo(Game game) {
        if (!history.isEmpty()) {
            game.restoreFromCache(history.pop());
        } else {
            System.out.println("No saves to restore!");
        }
    }
}

