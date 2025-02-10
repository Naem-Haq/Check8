package com.check.game;

import java.util.Stack;

public class GameCaretaker {
    private final Stack<GameMemento> history = new Stack<>();
    
    public void save(GameMemento memento) {
        history.push(memento);
    }
    
    public GameMemento restore() {
        return history.isEmpty() ? null : history.pop();
    }
}
