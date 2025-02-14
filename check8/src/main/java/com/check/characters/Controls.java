package com.check.characters;

public class Controls {
    private CharacterCommand[] buttons = new CharacterCommand[4];
    private Character player;
    private static final int ATTACK = 0;
    private static final int USE_HEAL_POTION = 1;
    private static final int USE_DAMAGE_POTION = 2;
    private static final int DODGE = 3;


    public Controls(Character player) {
        this.player = player;
        // Hard coded commands
        buttons[ATTACK] = new AttackCommand(player);
        buttons[USE_HEAL_POTION] = new UseHealPotionCommand(player);
        buttons[USE_DAMAGE_POTION] = new UseDamagePotionCommand(player);
        buttons[DODGE] = new DodgeCommand(player);
        // TODO: INFO Log "Controls created for " + player.getName()

    }

    public void pressButton(int index, Character target) {
        buttons[index].execute(target);
        // TODO: INFO Log "Character " + player.getName() + " pressed button " + index + " against " + target.getName()
    }

    public static int getAttack(){
        return ATTACK;
    }

    public static int getUseHealPotion(){
        return USE_HEAL_POTION;
    }

    public static int getUseDamagePotion(){
        return USE_DAMAGE_POTION;
    }

    public static int getDodge(){
        return DODGE;
    }
}
