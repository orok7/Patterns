package com.eins.learn.patterns.command;

public class MoveCom implements Command {
    private FileManager fileManager;

    public MoveCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 2) {
            fileManager.move(args[0], args[1]);
            fileManager.printHeader();
        } else {
            fileManager.printHeader();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void undo(String[] args) {

    }
}
