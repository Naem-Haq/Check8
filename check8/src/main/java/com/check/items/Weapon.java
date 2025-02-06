package com.check.items;

public abstract class Weapon extends Item {

    private int damage;
    private String description;

    public Weapon(int damage, String description) {
        super();
        this.damage = damage;
        this.description = description;
    }

    public abstract String attackText();

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }
    
}
