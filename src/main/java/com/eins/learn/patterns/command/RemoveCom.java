package com.eins.learn.patterns.command;

public class RemoveCom implements Command {
    private FileManager fileManager;

    public RemoveCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 1) {
            fileManager.remove(args[0]);
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
