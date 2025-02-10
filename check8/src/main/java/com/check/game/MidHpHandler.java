package com.check.game;

class MidHPHandler extends Handler {
    @Override
    void handleCharacterDecision(Character character) {
        if (character.getHealth() > 50) {
            System.out.println("Character at mid health, cautious attack.");
        } else if (nextHandler != null) {
            nextHandler.handleCharacterDecision(character);
        }
    }
}
