package com.check.characters;

public interface CharacterCommand {
    void execute(Character target);

    String executionText();
}
