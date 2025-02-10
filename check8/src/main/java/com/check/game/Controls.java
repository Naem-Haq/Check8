package com.check.game;

public class Controls {
    private Command[] buttons;
    
    public Controls(int buttonCount) {
        buttons = new Command[buttonCount];
    }
    
    public void setCommand(int index, Command command) {
        buttons[index] = command;
    }
    
    public void pressButton(int index) {
        if (buttons[index] != null) {
            buttons[index].execute();
        }
    }
}
