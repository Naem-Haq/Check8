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
    public void execute(Character target) {
        logger.debug("Executing Use Damage Potion command by {} against {}", executer.getName(), target.getName());
        this.executer.getInventory().useItem("DamagePotion", target);
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " uses a damage potion!";
    }
}
