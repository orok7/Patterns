package patterns.memento;

public class StateManager {
    private GameStateSnapshot snapshot;
    private Game game;

    public StateManager(Game game) {
        this.game = game;
    }

    public void save() {
        snapshot = game.createSnapshot();
        System.out.println("Game saved");
    }

    public void load() {
        game.restore(snapshot);
        System.out.println("Game loaded");
    }

}
