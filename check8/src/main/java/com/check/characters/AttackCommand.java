package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttackCommand implements CharacterCommand {
    private static Logger logger = LoggerFactory.getLogger(AttackCommand.class.getName());

    private Character executer;

    public AttackCommand(Character executer) {
        this.executer = executer;
    }

    @Override
    public String execute(Character target) {
        if (!target.isAttackable()) {
            return executer.getName() + "'s attack failed";
        }
        this.executer.getWeapon().attack(target);
        logger.debug("Attack command executed by {} against {}", executer.getName(), target.getName());
        return this.executer.getName() + " attacks!";
    }
}
