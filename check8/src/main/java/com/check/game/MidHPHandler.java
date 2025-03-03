package com.check.game;

import com.check.characters.Character;
import com.check.characters.DodgeCommand;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MidHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(MidHPHandler.class.getName());
    private static final double DODGE_PROBABILITY = 0.4;  // 40% chance to dodge at mid health

    public MidHPHandler() {
        super();
    }

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage >= LOW_HEALTH_P && healthPercentage < MID_HEALTH_P) {
            if (character.canDodge() && random.nextDouble() < DODGE_PROBABILITY) {
                logger.debug("Mid health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else {
                logger.debug("Mid health: choosing to attack");
                character.setAttackable(true);
                return Controls.getAttack();
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character);
        }
        return Controls.getAttack();
    }
}