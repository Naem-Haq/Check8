package com.check.characters;

import com.check.items.Inventory;
import com.check.items.Weapon;

public abstract class Character {

    private String name;
    private String description;
    private boolean cpu;
    private HealthBar healthBar = new HealthBar();
    private Inventory inventory = new Inventory();
    private Weapon weapon;

    public Character(String name, Weapon weapon, boolean cpu, String description) {
        this.name = name;
        this.weapon = weapon;
        this.cpu = cpu;
        this.description = description;
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

}
