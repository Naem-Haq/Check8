package com.check.game;

public class UsePotion implements CharacterCommand {
    private final Character character;
    
    public UsePotion(Character character) {
        this.character = character;
    }
    
    @Override
    public void execute() {
        character.usePotion();
    }
}
