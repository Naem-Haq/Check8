package com.check.game;

import com.check.characters.Character;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class MidHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(MidHPHandler.class.getName());
    private static final Random random = new Random();
    private static final double DODGE_PROBABILITY = 0.3;  // 30%
    private static final double DAMAGE_POTION_PROBABILITY = 0.4;  // 40%
    private static final double HEAL_POTION_PROBABILITY = 0.3;  // 30%

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = calculateHealthPercentage(character);
        if (healthPercentage >= LOW_HEALTH_P && healthPercentage < MID_HEALTH_P) {
            double rand = random.nextDouble();
            if (rand < DODGE_PROBABILITY) {
                logger.debug("Mid health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else if (rand < DODGE_PROBABILITY + DAMAGE_POTION_PROBABILITY) {
                if (character.getInventory().hasDamagePotion()) {
                    logger.debug("Mid health: using damage potion");
                    return Controls.getUseDamagePotion();
                } else {
                    logger.debug("Damage potion not available in mid health, defaulting to attack");
                    return Controls.getAttack();
                }
            } else if (rand < DODGE_PROBABILITY + DAMAGE_POTION_PROBABILITY + HEAL_POTION_PROBABILITY) {
                if (character.getInventory().hasHealPotion()) {
                    logger.debug("Mid health: using heal potion");
                    return Controls.getUseHealPotion();
                } else {
                    logger.debug("Heal potion not available in mid health, defaulting to attack");
                    return Controls.getAttack();
                }
            } else {
                logger.debug("Mid health: choosing to attack");
                return Controls.getAttack();
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character);
        }
        return Controls.getAttack();
    }
}