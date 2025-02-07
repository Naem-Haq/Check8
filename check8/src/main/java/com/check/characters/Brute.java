package com.check.characters;

import com.check.items.Axe;

public class Brute extends Character{

    public Brute(boolean cpu){
        super("Brute", new Axe(), cpu, """
        A brute character who uses his strength to defeat his enemies.
        He is a master of close combat and can overpower any enemy that comes his way.
        He is a fierce and powerful warrior who can crush his enemies with his bare hands and large axe.
        """);
    }
}
