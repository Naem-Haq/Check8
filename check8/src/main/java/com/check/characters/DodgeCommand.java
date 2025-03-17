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
    public String execute(Character target) {
        if (executer.canDodge()) {
            executer.setAttackable(false);
            executer.useDodge();
            logger.debug("Dodge command executed by {} ({} dodges remaining)", 
                executer.getName(), executer.getRemainingDodges());
            return this.executer.getName() + " dodges! (" + executer.getRemainingDodges() + " dodges remaining)";
        } else {
            logger.debug("{} cannot dodge - no dodges remaining", executer.getName());
            return this.executer.getName() + " tries to dodge but has no dodges remaining!";
        }
    }
    
}
