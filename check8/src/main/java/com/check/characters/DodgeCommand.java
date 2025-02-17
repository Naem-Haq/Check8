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
        if (executer.canDodge()) {
            executer.setAttackable(false);
            executer.useDodge();
            logger.debug("Dodge command executed by {} ({} dodges remaining)", 
                executer.getName(), executer.getRemainingDodges());
        } else {
            logger.debug("{} cannot dodge - no dodges remaining", executer.getName());
            System.out.println(executer.getName() + " has no dodges remaining!");
        }
    }

    @Override
    public String executionText() {
        if (executer.canDodge()) {
            return this.executer.getName() + " dodges! (" + executer.getRemainingDodges() + " dodges remaining)";
        } else {
            return this.executer.getName() + " tries to dodge but has no dodges remaining!";
        }
    }
    
}
