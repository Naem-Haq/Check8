package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(FullHPHandler.class.getName());
    private static final double DODGE_PROBABILITY = 0.2;  // 20% chance to dodge at full health
    private static final double DAMAGE_POTION_PROBABILITY = 0.5;  // 50% chance to use damage potion at full health
    private static final double HEAL_POTION_PROBABILITY = 0.3;  // 30% chance to use heal potion at full health

    public FullHPHandler() {
        super();
    }

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage >= MID_HEALTH_P) {
            if (character.hasItem("DamagePotion") && random.nextDouble() < DAMAGE_POTION_PROBABILITY) {
                logger.debug("Full health: using damage potion");
                return Controls.getUseDamagePotion();
            } else if (character.hasItem("HealPotion") && random.nextDouble() < HEAL_POTION_PROBABILITY) {
                logger.debug("Full health: using heal potion");
                return Controls.getUseHealPotion();
            } else if (character.canDodge() && random.nextDouble() < DODGE_PROBABILITY) {
                logger.debug("Full health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else {
                logger.debug("Full health: choosing to attack");
                character.setAttackable(true);
                return Controls.getAttack();
            }
        }
        return Controls.getAttack();
    }
}