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
    public void execute(Character target) {
        if (!target.isAttackable()) {
            System.out.println(target.getName() + " dodged the attack!");
            return;
        }

        int damage = executer.getWeapon().getDamage();
        target.getHealthBar().decreaseHealth(damage);
        logger.debug("{} deals {} damage to {}", 
            executer.getName(), damage, target.getName());
    }

    @Override
    public String executionText() {
        return executer.getName() + " attacks!";
    }
    
}
