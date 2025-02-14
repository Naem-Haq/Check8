package com.check.items;
import com.check.characters.Character;

public abstract class Weapon{

    private int damage;
    private String description;

    public Weapon(int damage, String description) {
        super();
        this.damage = damage;
        this.description = description;
    }

    public void attack(Character enemy){
        enemy.getHealthBar().decreaseHealth(this.getDamage());
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
