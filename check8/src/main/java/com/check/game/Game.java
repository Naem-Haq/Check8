package com.check.game;

public class Game {
    private int rounds;
    private GameState state;
    private final GameCaretaker caretaker;
    
    public Game() {
        this.state = new Ready();
        this.caretaker = new GameCaretaker();
    }
    
    public void requestStateChange() {
        state.handleRequest(this);
    }
    
    public void setState(GameState state) {
        this.state = state;
    }
    
    public void saveState() {
        caretaker.save(new GameMemento(rounds, state));
        System.out.println("Game state saved.");
    }
    
    public void restoreState() {
        GameMemento memento = caretaker.restore();
        if (memento != null) {
            this.rounds = memento.getRounds();
            this.state = memento.getState();
            System.out.println("Game state restored.");
        } else {
            System.out.println("No saved states available.");
        }
    }
}
