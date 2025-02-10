package com.check.data;

import java.util.HashMap;
import java.util.Map;

//Memento Design Pattern - Memento
public class GameCache {
    private final Map<String, Object> state;

    public GameCache(Map<String, Object> state) {
        this.state = new HashMap<>(state); // Deep copy to prevent modification
    }

    public Map<String, Object> getState() {
        return new HashMap<>(state);

    }
}
