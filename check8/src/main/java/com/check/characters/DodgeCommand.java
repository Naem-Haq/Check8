package com.check.characters;

public class DodgeCommand implements CharacterCommand {

    private Character executer;

    public DodgeCommand(Character executer) {
        this.executer = executer;
    }

    @Override
    public void execute(Character target) {
        executer.setAttackable(false);
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " dodges!";
    }
    
}
