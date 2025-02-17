package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.check.items.Inventory;
import com.check.items.Weapon;

public abstract class Character {
    private static Logger logger = LoggerFactory.getLogger(Character.class.getName());
    private static final int MAX_DODGES = 3;
    private int remainingDodges;

    private String name;
    private String description;
    private boolean cpu;
    private HealthBar healthBar = new HealthBar();
    private Inventory inventory = new Inventory();
    private Weapon weapon;
    private boolean attackable = true;

    public Character(String name, Weapon weapon, boolean cpu, String description) {
        this.name = name;
        this.weapon = weapon;
        this.cpu = cpu;
        this.description = description;
        this.remainingDodges = MAX_DODGES;
        logger.info("Character ({}) created", this.name);
    }

    public void populateInventory() {
        logger.info("Character {} inventory populated", this.name);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isCPU() {
        return cpu;
    }

    public void setCPU(boolean cpu) {
        this.cpu = cpu;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }

    public boolean canDodge() {
        return remainingDodges > 0;
    }

    public void useDodge() {
        if (remainingDodges > 0) {
            remainingDodges--;
        }
    }

    public int getRemainingDodges() {
        return remainingDodges;
    }
}
