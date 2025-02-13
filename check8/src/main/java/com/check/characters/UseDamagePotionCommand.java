package com.check.characters;

public class UseDamagePotionCommand implements CharacterCommand {

    private Character executer;

    public UseDamagePotionCommand(Character executer) {
        this.executer = executer;
    }
    
    @Override
    public void execute(Character target) {
        this.executer.getInventory().useItem("DamagePotion", target);

        // TODO: DEBUG Log "Use Damage Potion command executed by " + executer.getName() + " against " + target.getName()

    }

    @Override
    public String executionText() {
        return this.executer.getName() + " uses a damage potion!";
    }
    
}
