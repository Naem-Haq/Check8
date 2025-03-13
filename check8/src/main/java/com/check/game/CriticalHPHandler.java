package com.check.game;

import com.check.characters.Character;
import com.check.characters.DodgeCommand;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;
import com.check.characters.Controls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class CriticalHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(CriticalHPHandler.class.getName());
    private static final Random random = new Random();
    private static final double DODGE_PROBABILITY = 0.8;  // 80% chance to dodge when critical

    public CriticalHPHandler() {
        super();
    }

    @Override
    public int handleCharacterDecision(Character character) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / 
            character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage < CRITICAL_HEALTH_P) {
            if (character.hasItem("HealPotion")) {
                logger.debug("Critical health: using heal potion");
                return Controls.getUseHealPotion();
            } else if (character.canDodge() && random.nextDouble() < DODGE_PROBABILITY) {
                logger.debug("Critical health: choosing to dodge");
                character.setAttackable(false);
                return Controls.getDodge();
            } else {
                logger.debug("Critical health: choosing to attack");
                character.setAttackable(true);
                return Controls.getAttack();
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character);
        }
        return Controls.getAttack();
    }
}
