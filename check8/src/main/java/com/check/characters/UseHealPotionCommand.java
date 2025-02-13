package com.check.characters;

public class UseHealPotionCommand implements CharacterCommand {

    private Character executer;

    public UseHealPotionCommand(Character executer) {
        this.executer = executer;
    }
    
    @Override
    public void execute(Character target) {
        this.executer.getInventory().useItem("HealPotion", target);
        // TODO: DEBUG Log "Use Heal Potion command executed by " + executer.getName() + " against " + target.getName()
    }

    @Override
    public String executionText() {
        return this.executer.getName() + " uses a healing potion!";
    }
}
