package com.check.characters;

public class DodgeCommand implements CharacterCommand {

    private Character executer;

    public DodgeCommand(Character executer) {
        this.executer = executer;
    }

    @Override
    public void execute(Character target) {
        executer.setAttackable(false);
        // TODO: DEBUG Log "Dodge command executed by " + executer.getName()
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " dodges!";
    }
    
}
