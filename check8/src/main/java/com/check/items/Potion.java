package com.check.items;

public abstract class Potion extends Item {

    private int healthPoints;

    public Potion(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public abstract String useText();

    public int getHealthPoints() {
        return healthPoints;
    }
    
}
