package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controls {
    private static Logger logger = LoggerFactory.getLogger(Controls.class.getName());

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
        logger.debug("Controls created for {}", player.getName());

    }

    public void pressButton(int index, Character target) {
        buttons[index].execute(target);
        logger.info("Character {} pressed button {} against {}", player.getName(), buttons[index].getClass().getSimpleName(), target.getName());
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
