package com.check.items;
import com.check.characters.Character;

public class DamagePotion extends Potion{

    public DamagePotion() {
        super(15);
    }

    @Override
    public String useText(){
        return "You throw a damage potion at the enemy!";
    }

    @Override
    public void use(Character enemy) {
        enemy.getHealthBar().decreaseHealth(this.getHealthPoints());
    }
    
}
