package com.check.game;

public class Dodge implements CharacterCommand {
    private final Character character;
    
    public Dodge(Character character) {
        this.character = character;
    }
    
    @Override
    public void execute() {
        character.dodge();
    }
}
