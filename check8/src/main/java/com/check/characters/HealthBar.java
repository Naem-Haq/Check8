package com.check.characters;

import java.util.ArrayList;

public class HealthBar implements Subject{
    private int health;
    private int maxHealth = 100;
    private ArrayList<HealthObserver> healthObservers = new ArrayList<HealthObserver>();

    public HealthBar(){
        this.health = maxHealth;
    }

    public HealthBar(int maxHealth){
        this.health = maxHealth;
    }

    public void setHealth(int health){
        if (health < 0){
            this.health = 0;
            updateAll();
            return;
        } else if (health > this.maxHealth){
            this.health = this.maxHealth;
            updateAll();
            return;
        }
        this.health = health;
        updateAll();
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void increaseHealth(int increase){
        setHealth(this.health + increase);
    }

    public void decreaseHealth(int reduction){
        setHealth(this.health - reduction);
    }

    public int getHealth(){
        return health;
    }

    public void attach(HealthObserver healthObserver){
        this.healthObservers.add(healthObserver);
    }

    public void detach(HealthObserver healthObserver){
        this.healthObservers.remove(healthObserver);
    }

    public void updateAll(){
        for (HealthObserver healthObserver : healthObservers){
            healthObserver.update(getHealth());
        }
    }
    
}
