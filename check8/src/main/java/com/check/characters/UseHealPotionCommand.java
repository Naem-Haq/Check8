package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseHealPotionCommand implements CharacterCommand {

    private static Logger logger = LoggerFactory.getLogger(UseHealPotionCommand.class.getName());

    private Character executer;

    public UseHealPotionCommand(Character executer) {
        this.executer = executer;
    }
    
    @Override
    public void execute(Character target) {
        this.executer.getInventory().useItem("HealPotion", target);
        logger.debug("Use Heal Potion command executed by {} against {}", executer.getName(), target.getName());
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " uses a healing potion!";
    }
}
