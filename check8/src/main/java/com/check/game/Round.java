package com.check.game;

public class Round {
    package com.check.game;

import java.util.Stack;

// Memento Pattern - Saving and Restoring Game State
class GameMemento {
    private final int rounds;
    private final GameState state;
    
    public GameMemento(int rounds, GameState state) {
        this.rounds = rounds;
        this.state = state;
    }
    
    public int getRounds() { return rounds; }
    public GameState getState() { return state; }
}

class GameCaretaker {
    private final Stack<GameMemento> history = new Stack<>();
    
    public void save(GameMemento memento) {
        history.push(memento);
    }
    
    public GameMemento restore() {
        return history.isEmpty() ? null : history.pop();
    }
}

// State Pattern - Game State Management
abstract class GameState {
    public abstract void handleRequest(Game game);
}

class Ready extends GameState {
    public void handleRequest(Game game) {
        System.out.println("Game is now starting...");
        game.setState(new InProgress());
    }
}

class InProgress extends GameState {
    public void handleRequest(Game game) {
        System.out.println("Game is already in progress.");
    }
}

// Game Class utilizing State & Memento Patterns
class Game {
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

// Chain of Responsibility - CPU Attack Decisions
abstract class Handler {
    protected Handler nextHandler;
    
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public abstract void handleCharacterDecision(Character character);
}

class FullHPHandler extends Handler {
    public void handleCharacterDecision(Character character) {
        if (character.getHealth() > 80) {
            System.out.println("CPU chooses aggressive attack!");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}

class LowHPHandler extends Handler {
    public void handleCharacterDecision(Character character) {
        if (character.getHealth() <= 40) {
            System.out.println("CPU chooses defensive maneuver!");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}

class CPU {
    private final Handler handlerChain;
    
    public CPU() {
        Handler fullHP = new FullHPHandler();
        Handler lowHP = new LowHPHandler();
        
        fullHP.setNextHandler(lowHP);
        this.handlerChain = fullHP;
    }
    
    public void generateMove(Character character) {
        handlerChain.handleCharacterDecision(character);
    }
}

// Round Class
class Round {
    private final Character[] characters;
    private final CPU cpu;
    
    public Round(Character[] characters) {
        this.characters = characters;
        this.cpu = new CPU();
    }
    
    public void executeRound() {
        for (Character character : characters) {
            if (character.isCPU()) {
                cpu.generateMove(character);
            } else {
                System.out.println("Player turn: " + character.getName());
            }
        }
    }
}
