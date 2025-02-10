package com.check.game;

// CPU CLASS HANDLING AI DECISIONS
class CPU {
    public static void generateMove(CharacterCommand character) {
        CharacterCommand action;
        if (character.getHealth() < 50) {
            action = new Dodge(character);
        } else {
            action = new Attack(character);
        }
        action.execute();
    }
}
