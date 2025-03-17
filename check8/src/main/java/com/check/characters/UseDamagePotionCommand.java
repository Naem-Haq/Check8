package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseDamagePotionCommand implements CharacterCommand {

    private static Logger logger = LoggerFactory.getLogger(UseDamagePotionCommand.class.getName());

    private Character executer;

    public UseDamagePotionCommand(Character executer) {
        this.executer = executer;
    }
    
    @Override
    public String execute(Character target) {
        logger.debug("Executing Use Damage Potion command by {} against {}", executer.getName(), target.getName());
        this.executer.getInventory().useItem("DamagePotion", target);
        return this.executer.getName() + " uses a damage potion!";
    }
}
