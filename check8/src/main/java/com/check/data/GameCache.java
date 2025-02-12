package com.check.data;

import com.check.game.GameState;
import com.check.characters.Character;

import java.util.ArrayList;
import java.util.List;

//Memento Design Pattern - Memento
public class GameCache{
    private final List<Character> characters;
    private final GameState state;
    private final int rounds;

    public GameCache(List<Character> characters, GameState state, int rounds) {
        this.characters = new ArrayList<>(characters);
        this.state = state;
        this.rounds = rounds;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public int getRounds() {
        return rounds;
    }
    public GameState getState() {
        return state;
    }

}
