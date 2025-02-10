package com.check.game;

class CriticalHPHandler extends Handler {
    @Override
    void handleCharacterDecision(Character character) {
        System.out.println("Character at critical health, taking defensive action.");
    }
}
