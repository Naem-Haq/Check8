package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class Controls {
    private static Logger logger = LoggerFactory.getLogger(Controls.class.getName());
    private static final int NUM_COMMANDS = 4;
    private List<CharacterCommand> commands;
    private Character character;

    // Command indices as constants
    private static final int ATTACK = 0;
    private static final int DODGE = 1;
    private static final int USE_HEAL_POTION = 2;
    private static final int USE_DAMAGE_POTION = 3;

    public Controls(Character character) {
        this.character = character;
        this.commands = new ArrayList<>(NUM_COMMANDS);
        
        // Initialize list with nulls
        for (int i = 0; i < NUM_COMMANDS; i++) {
            commands.add(null);
        }

        // Set commands at specific indices
        commands.set(ATTACK, new AttackCommand(character));
        commands.set(DODGE, new DodgeCommand(character));
        commands.set(USE_HEAL_POTION, new UseHealPotionCommand(character));
        commands.set(USE_DAMAGE_POTION, new UseDamagePotionCommand(character));
        
        logger.debug("Controls created for {}", character.getName());
    }

    public void pressButton(int index, Character target) {
        if (index >= 0 && index < commands.size()) {
            CharacterCommand command = commands.get(index);
            command.execute(target);
            System.out.println(command.executionText());
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

    // Getter methods for command indices
    public static int getAttack() { return ATTACK; }
    public static int getDodge() { return DODGE; }
    public static int getUseHealPotion() { return USE_HEAL_POTION; }
    public static int getUseDamagePotion() { return USE_DAMAGE_POTION; }
}
