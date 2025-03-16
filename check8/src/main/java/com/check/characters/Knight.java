package com.check.characters;

import java.util.Arrays;

import com.check.items.DamagePotion;
import com.check.items.HealPotion;
import com.check.items.Item;
import com.check.items.Sword;

public class Knight extends Character{

    public Knight(boolean cpu){
        super("Knight", new Sword(), cpu, """
        A mighty knight who fights for justice for all who support the queen.
        He is a strong and brave warrior who is always ready to fight for his kingdom.
        He is a master of the sword and can defeat any enemy that comes his way.
        """);
        populateInventory();
    }

    @Override
    public void populateInventory() {
        Item[] items = new Item[]{new HealPotion(), new HealPotion(), new DamagePotion(), new DamagePotion()};
        getInventory().addItems(Arrays.asList(items));
        super.populateInventory();
    }
}
