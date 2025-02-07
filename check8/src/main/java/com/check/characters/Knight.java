package com.check.characters;

import com.check.items.Sword;

public class Knight extends Character{

    public Knight(boolean cpu){
        super("Knight", new Sword(), cpu, """
        A mighty knight who fights for justice for all who support the queen.
        He is a strong and brave warrior who is always ready to fight for his kingdom.
        He is a master of the sword and can defeat any enemy that comes his way.
        """);
    }
}
