package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class Controls {
    private static Logger logger = LoggerFactory.getLogger(Controls.class.getName());
    private Character character;
    private List<CharacterCommand> commands;

    public Controls(Character character) {
        this.character = character;
        this.commands = new ArrayList<>();
        commands.add(new AttackCommand(character));   // index 0
        commands.add(new DodgeCommand(character));    // index 1
        commands.add(new UseHealPotionCommand(character));  // index 2
        commands.add(new UseDamagePotionCommand(character));  // index 3
    }

    public void pressButton(int index, Character target) {
        if (index >= 0 && index < commands.size()) {
            CharacterCommand command = commands.get(index);
            command.execute(target);
            System.out.println(command.executionText());  // Print what the player does
            logger.info("Character {} pressed button {} against {}", 
                character.getName(), command.getClass().getSimpleName(), target.getName());
        }
    }

    public CharacterCommand getCommand(int index) {
        if (index >= 0 && index < commands.size()) {
            return commands.get(index);
        }
        throw new IllegalArgumentException("Invalid command index");
    }

    public static int getAttack(){
        return 0;
    }

    public static int getUseHealPotion(){
        return 2;
    }

    public static int getUseDamagePotion(){
        return 3;
    }

    public static int getDodge(){
        return 1;
    }
}
