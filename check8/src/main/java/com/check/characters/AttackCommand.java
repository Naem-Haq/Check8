package com.check.characters;

public class AttackCommand implements CharacterCommand {

    private Character executer;

    public AttackCommand(Character executer) {
        this.executer = executer;
    }

    @Override
    public void execute(Character target) {
        if (!target.isAttackable()) {
            return;
        }
        this.executer.getWeapon().attack(target);
        // TODO: DEBUG Log "Attack command executed by " + executer.getName() + " against " + target.getName()
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " attacks!";
    }
    
}
