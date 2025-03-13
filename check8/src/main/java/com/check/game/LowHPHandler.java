package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LowHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(LowHPHandler.class.getName());
    private static final double DODGE_PROBABILITY = 0.6;  // 60% chance to dodge when low

    public LowHPHandler() {
        super();
    }

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage < LOW_HEALTH_P) {
            if (character.hasItem("HealPotion")) {
                logger.debug("Low health: using heal potion");
                return Controls.getUseHealPotion();
            } else if (character.canDodge() && random.nextDouble() < DODGE_PROBABILITY) {
                logger.debug("Low health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else {
                logger.debug("Low health: choosing to attack");
                character.setAttackable(true);
                return Controls.getAttack();
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character);
        }
        return Controls.getAttack();
    }
}