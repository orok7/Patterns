package com.eins.learn.patterns.command;

public class ChangeDirCom implements Command {
    private FileManager fileManager;

    public ChangeDirCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 1) {
            fileManager.changeDir(args[0]);
            fileManager.printHeader();
        } else {
            fileManager.printHeader();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void undo(String[] args) {}
}
