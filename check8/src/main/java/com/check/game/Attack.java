package com.check.game;

public class Attack implements CharacterCommand {
    private final Character character;
    
    public Attack(Character character) {
        this.character = character;
    }
    
    @Override
    public void execute() {
        character.attack();
    }
}
