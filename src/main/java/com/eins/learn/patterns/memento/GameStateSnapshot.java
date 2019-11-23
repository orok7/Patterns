package com.eins.learn.patterns.memento;

import lombok.Getter;

public class GameStateSnapshot {
    @Getter
    private final int level;
    @Getter
    private final double solvedPercents;

    public GameStateSnapshot(Game state) {
        this.level = state.getLevel();
        this.solvedPercents = state.getSolvedPercents();
    }

    public GameStateSnapshot getState() {
        return this;
    }

}
