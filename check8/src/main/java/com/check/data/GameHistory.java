package com.check.data;

import java.util.Stack;

//caretaker
public class GameHistory {
    private final Stack<GameCache> history = new Stack<>();

    public void save(GameData gameData) {
        history.push(gameData.save());
    }

    public void undo(GameData character) {
        if (!history.isEmpty()) {
            character.restore(history.pop());
        } else {
            System.out.println("No saves to restore!");
        }
    }
}

