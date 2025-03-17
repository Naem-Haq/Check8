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
    public String execute(Character target) {
        logger.debug("Executing Use Heal Potion command by {} (applied on self)", executer.getName());
        this.executer.getInventory().useItem("HealPotion", executer);
        return this.executer.getName() + " uses a heal potion!";
    }
}
