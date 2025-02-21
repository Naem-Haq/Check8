package com.check.game;

import com.check.characters.Character;
import com.check.characters.CharacterCommand;
import java.util.Random;

// CHAIN OF RESPONSIBILITY PATTERN FOR CHARACTER DECISIONS
public abstract class Handler {
    protected Handler nextHandler;
    protected static final Random random = new Random();  // Add Random for all handlers
    
    // Probability constants
    protected static final double SIXTY_PERCENT = 0.6;
    protected static final double DODGE_CHANCE = SIXTY_PERCENT;  // 60% chance to dodge when possible
    
    // Health thresholds as constants
    protected static final double CRITICAL_HEALTH = 25.0;
    protected static final double LOW_HEALTH = 50.0;
    protected static final double MID_HEALTH = 75.0;
    protected static final double PERCENTAGE_MULTIPLIER = 100.0;  // Add this constant

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public abstract CharacterCommand handleCharacterDecision(Character character, Character target);
}