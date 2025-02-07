package com.check.characters;

import com.check.items.Bow;

public class Archer extends Character{

    public Archer(boolean cpu){
        super("Archer", new Bow(), cpu, """
        A skilled archer who uses his bow to defeat his enemies from a distance.
        He is a master of the bow and can hit any target with deadly accuracy.
        He is a quick and agile warrior who can move swiftly to avoid his enemies' attacks.
        """);
    }
}
