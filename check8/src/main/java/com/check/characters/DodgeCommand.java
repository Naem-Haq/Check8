package com.check.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DodgeCommand implements CharacterCommand {
    private static Logger logger = LoggerFactory.getLogger(DodgeCommand.class.getName());

    private Character executer;

    public DodgeCommand(Character executer) {
        this.executer = executer;
    }

    @Override
    public void execute(Character target) {
        executer.setAttackable(false);
        logger.debug("Dodge command executed by {}", executer.getName());
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " dodges!";
    }
    
}
