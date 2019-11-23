package com.eins.learn.patterns.command;

public class NewDirCom implements Command {
    private FileManager fileManager;

    public NewDirCom(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args != null && args.length == 1) {
            fileManager.newDirectory(args[0]);
            fileManager.printHeader();
        } else {
            fileManager.printHeader();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void undo(String[] args) {
        new RemoveCom(fileManager).execute(args);
    }
}
