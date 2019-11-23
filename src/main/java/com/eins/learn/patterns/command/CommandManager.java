package com.eins.learn.patterns.command;

import lombok.Getter;

import java.util.LinkedList;

public class CommandManager {
    private final int capacity;
    private LinkedList<DoneCommand> doneList = new LinkedList<>();
    private LinkedList<DoneCommand> undoneList = new LinkedList<>();

    public CommandManager(int capacity) {
        this.capacity = capacity;
    }

    void execute(Command command, String[] args) {
        undoneList = new LinkedList<>();
        command.execute(args);
        doneList.addLast(new DoneCommand(command, args));
        if (doneList.size() > capacity) {
            doneList.removeFirst();
        }
    }

    void undo() {
        if (!doneList.isEmpty()) {
            DoneCommand doneCommand = doneList.pollLast();
            undoneList.addLast(doneCommand);
            doneCommand.undo();
        }
    }

    void redo() {
        if (!undoneList.isEmpty()) {
            DoneCommand redoCommand = undoneList.pollLast();
            doneList.addLast(redoCommand);
            redoCommand.execute();
        }
    }

    private class DoneCommand {
        @Getter
        private final Command command;
        @Getter
        private final String[] args;

        DoneCommand(Command command, String[] args) {
            this.command = command;
            this.args = args;
        }

        void execute() {
            command.execute(args);
        }

        void undo() {
            command.undo(args);
        }
    }

}
