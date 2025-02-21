package com.check.game;

import com.check.characters.Character;
import com.check.characters.DodgeCommand;
import com.check.characters.AttackCommand;
import com.check.characters.CharacterCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class LowHPHandler extends Handler {
    private static Logger logger = LoggerFactory.getLogger(LowHPHandler.class.getName());
    private static final Random random = new Random();

    @Override
    public CharacterCommand handleCharacterDecision(Character character, Character target) {
        double healthPercentage = (double) character.getHealthBar().getHealth() / character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
        if (healthPercentage < LOW_HEALTH) {
            if (character.canDodge() && random.nextDouble() < DODGE_CHANCE) {
                logger.debug("Low health: choosing to dodge");
                character.setAttackable(false);
                return new DodgeCommand(character);
            } else {
                logger.debug("Low health: choosing to attack");
                character.setAttackable(true);
                return new AttackCommand(character);
            }
        } else if (nextHandler != null) {
            return nextHandler.handleCharacterDecision(character, target);
        }
        return new AttackCommand(character);
    }
}