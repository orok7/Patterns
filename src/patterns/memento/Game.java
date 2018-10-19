package patterns.memento;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Game {
    @Getter
    private int level;
    @Getter
    private double solvedPercents;

    public Game() {
        this.level = 1;
        this.solvedPercents = 0.0;
    }

    public void changeState(int level, double solvedPercents) {
        this.level = level;
        this.solvedPercents = solvedPercents;
    }

    public GameStateSnapshot createSnapshot() {
        return new GameStateSnapshot(this);
    }

    public void restore(GameStateSnapshot snapshot) {
        level = snapshot.getLevel();
        solvedPercents = snapshot.getSolvedPercents();
    }
}
