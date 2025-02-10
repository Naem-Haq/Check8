package com.check.game;

// MEMENTO PATTERN FOR SAVING GAME STATE
class GameCache {
    private final List<Character> characters;
    private final int rounds;

    public GameCache(List<Character> characters, int rounds) {
        this.characters = new ArrayList<>(characters);
        this.rounds = rounds;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public int getRounds() {
        return rounds;
    }
}
