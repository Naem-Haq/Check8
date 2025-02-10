package com.check.items;
import org.junit.Test;

import com.check.characters.Brute;

public class HealPotionTest {

    @Test
    public void testUseText(){
        HealPotion potion = new HealPotion();
        assert(potion.useText().equals("You drink a healing potion!"));
    }

    @Test
    public void testUse(){
        HealPotion potion = new HealPotion();
        Brute brute = new Brute(true);
        brute.getHealthBar().decreaseHealth(40);
        potion.use(brute);
        assert(brute.getHealthBar().getHealth() == 80);
    }
}
