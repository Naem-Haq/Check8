package com.check.items;
import org.junit.Test;


public class AxeTest {
    
    @Test
    public void testAttackText(){
        Axe axe = new Axe();
        assert("You swing your heavy axe at the enemy!".equals(axe.attackText()));
    }
    
    @Test
    public void testGetDamage(){
        Axe axe = new Axe();
        assert(35 == axe.getDamage());
    }
    
    @Test
    public void testSetDamage(){
        Axe axe = new Axe();
        axe.setDamage(40);
        assert(40 == axe.getDamage());
    }
    
    @Test
    public void testGetDescription(){
        Axe axe = new Axe();
        assert("A heavy axe to be wielded by a strong warrior".equals(axe.getDescription()));
    }
}
