package com.check.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Originator
public class GameData {
    private Character character;
    public void setState(Character character) {
        this.character = character;
    }
    public GameCache save() {
        Map<String, Object> state = new HashMap<>();
//        state.put("health", character.getHealthBar()
        return new GameCache(state);
    }
    public void restore(GameCache gameCache) {
        Map<String, Object> state = gameCache.getState();
//        this.character.setHealth((int) state.get("health"));
    }
}
