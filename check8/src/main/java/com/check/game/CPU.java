package com.check.game;

// CPU CLASS HANDLING AI DECISIONS
class CPU {
    public static void generateMove(com.check.characters.Character character) {
        CharacterCommand action;
        if (character.getHealthBar().getHealth() < 50) {
            action = new DodgeCommand(character);
        } else {
            action = new AttackCommand(character);
        }
        action.execute();
    }
}
