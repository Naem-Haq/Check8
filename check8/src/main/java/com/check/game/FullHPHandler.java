package com.check.game;

import com.check.characters.Character;
import com.check.characters.CharacterCommand;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FullHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(FullHPHandler.class.getName());
    private static final double DODGE_PROBABILITY = 0.2;  // 20% chance to dodge at full health

    public FullHPHandler() {
        super();
    }

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage >= MID_HEALTH_P) {
            if (character.canDodge() && random.nextDouble() < DODGE_PROBABILITY) {
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