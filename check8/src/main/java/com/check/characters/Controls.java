package com.check.characters;

public class Controls {
    private CharacterCommand[] buttons = new CharacterCommand[4];
    private static final int ATTACK = 0;
    private static final int USE_HEAL_POTION = 1;
    private static final int USE_DAMAGE_POTION = 2;
    private static final int DODGE = 3;


    public Controls(Character player) {
        // Hard coded commands
        buttons[ATTACK] = new AttackCommand(player);
        buttons[USE_HEAL_POTION] = new UseHealPotionCommand(player);
        buttons[USE_DAMAGE_POTION] = new UseDamagePotionCommand(player);
        buttons[DODGE] = new DodgeCommand(player);
    }

    public void pressButton(int index, Character target) {
        buttons[index].execute(target);
    }

    public static int getATTACK(){
        return ATTACK;
    }

    public static int getUSE_HEAL_POTION(){
        return USE_HEAL_POTION;
    }

    public static int getUSE_DAMAGE_POTION(){
        return USE_DAMAGE_POTION;
    }

    public static int getDODGE(){
        return DODGE;
    }
}
