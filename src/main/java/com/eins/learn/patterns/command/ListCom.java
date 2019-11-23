package com.eins.learn.patterns.command;

public class ListCom implements Command {
    private FileManager fileManager;

    public ListCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        fileManager.list();
        fileManager.printHeader();
    }

    @Override
    public void undo(String[] args) {}
}
