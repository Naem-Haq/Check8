package com.check.game;

import com.check.characters.Character;
import java.util.Random;

// CHAIN OF RESPONSIBILITY PATTERN FOR CHARACTER DECISIONS
public abstract class Handler {
    protected Handler nextHandler;
    protected static final Random random = new Random();  // Add Random for all handlers
    
    // Health thresholds as percentages
    protected static final double CRITICAL_HEALTH_P = 25.0;
    protected static final double LOW_HEALTH_P = 50.0;
    protected static final double MID_HEALTH_P = 75.0;
    protected static final double PERCENTAGE_MULTIPLIER = 100.0;  // Add this constant


    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public abstract int handleCharacterDecision(Character character);

    protected double calculateHealthPercentage(Character character) {
        return (double) character.getHealthBar().getHealth() / 
            character.getHealthBar().getMaxHealth() * PERCENTAGE_MULTIPLIER;
    }
}