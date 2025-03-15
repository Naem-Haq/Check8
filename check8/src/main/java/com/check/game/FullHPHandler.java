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
            double rand = random.nextDouble();
            if (rand < DODGE_PROBABILITY) {
                logger.debug("Full health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else if (rand < DODGE_PROBABILITY + DAMAGE_POTION_PROBABILITY) {
                logger.debug("Full health: using damage potion");
                return Controls.getUseDamagePotion();
            } else if (rand < DODGE_PROBABILITY + DAMAGE_POTION_PROBABILITY + HEAL_POTION_PROBABILITY) {
                logger.debug("Full health: using heal potion");
                return Controls.getUseHealPotion();
            } else {
                logger.debug("Full health: choosing to attack");
                return Controls.getAttack();
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character);
        }
        return Controls.getAttack();
    }
}