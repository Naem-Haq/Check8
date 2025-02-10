package com.check.items;
import com.check.characters.*;

import org.junit.Test;

public class DamagePotionTest {

    @Test
    public void testUseText(){
        DamagePotion potion = new DamagePotion();
        assert(potion.useText().equals("You throw a damage potion at the enemy!"));
    }

    @Test
    public void testUse(){
        DamagePotion potion = new DamagePotion();
        Brute enemy = new Brute(true);
        potion.use(enemy);
        assert(enemy.getHealthBar().getHealth() == 85);
    }
}
