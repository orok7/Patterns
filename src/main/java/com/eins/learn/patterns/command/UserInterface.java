package com.eins.learn.patterns.command;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserInterface {
    private Map<Commands, Command> commandsMap;

    private CommandManager commandManager = new CommandManager(5);

    public UserInterface() {
        printHelp();
        FileManager fileManager = new FileManager();
        commandsMap = new HashMap<>();
        commandsMap.put(Commands.TOUCH, new NewFileCom(fileManager));
        commandsMap.put(Commands.MKDIR, new NewDirCom(fileManager));
        commandsMap.put(Commands.REM, new RemoveCom(fileManager));
        commandsMap.put(Commands.RENAME, new RenameCom(fileManager));
        commandsMap.put(Commands.MOVE, new MoveCom(fileManager));
        commandsMap.put(Commands.DIR, new ListCom(fileManager));
        commandsMap.put(Commands.CD, new ChangeDirCom(fileManager));
    }

    private void printHelp() {
        Arrays.stream(Commands.values())
                .forEach(cmd -> System.out.println("|\t\t" + cmd.name().toLowerCase() + " - " + cmd.getDescription()));
        System.out.println("|\t\tDon't use spaces in file/directory names");
    }

    public void executeCommand(String commandLine) {
        if (commandLine != null && !commandLine.isEmpty()) {
            String[] split = commandLine.split(" ");
            if (split.length > 0) {
                Commands command = Commands.of(split[0].toUpperCase());
                if (command == null) {
                    System.out.println("Command " + split[0] + " not found");
                    return;
                }
                String[] args = new String[split.length - 1];
                System.arraycopy(split, 1, args, 0, split.length - 1);
                if (commandsMap.get(command) != null) {
                    try {
                        commandManager.execute(commandsMap.get(command), args);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Wrong arguments.\nExample: " + command.getExample());
                    }
                } else {
                    switch (command) {
                    case HELP:
                        printHelp();
                        break;
                    case UNDO:
                        commandManager.undo();
                        break;
                    case REDO:
                        commandManager.redo();
                        break;
                    }
                }
            }
        }
    }

    private enum Commands {
        DIR("Shows list of files and directories", "dir"), MKDIR("Creates a directory", "mkdir [directory name]"), CD(
                "Changes directory", "cd [directory name | .. | path]"), TOUCH("Creates a file",
                "touch [file name]"), REM("Removes a file/directory", "rem [directory/file name"), RENAME(
                "Renames a file/directory", "rename [old name] [new name]"), MOVE(
                "Move file/directory to another directory", "move [name] [destination path]"), HELP(
                "Shows commands list", "help"), UNDO("Undo the last command", "undo"), REDO(
                "Redo the last undone command", "undo");

        @Getter
        private final String description;
        @Getter
        private final String example;

        Commands(String description, String example) {
            this.description = description;
            this.example = example;
        }

        public static Commands of(String command) {
            try {
                return valueOf(command);
            } catch (Exception e) {
                return null;
            }
        }
    }

}
