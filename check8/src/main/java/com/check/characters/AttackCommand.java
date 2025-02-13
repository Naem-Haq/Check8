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
        int damage = this.executer.getWeapon().getDamage();
        target.getHealthBar().decreaseHealth(damage);
        // TODO: DEBUG Log "Attack command executed by " + executer.getName() + " against " + target.getName()
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " attacks!";
    }
    
}
