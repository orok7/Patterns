package com.eins.learn.patterns.memento;

public class MementoDemo {
    public static void main(String[] args) {
        Game game = new Game();
        StateManager stateManager = new StateManager(game);
        game.changeState(1, 25.0);
        System.out.println(game);

        game.changeState(1, 55.0);
        System.out.println(game);
        stateManager.save();

        game.changeState(1, 75.0);
        System.out.println(game);

        stateManager.load();
        System.out.println(game);

    }
}
