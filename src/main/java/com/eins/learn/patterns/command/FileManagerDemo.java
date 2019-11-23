package com.eins.learn.patterns.command;

import java.util.Scanner;

public class FileManagerDemo {

    //ToDo not finished yet
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        Scanner scanner = new Scanner(System.in);
        String commandLine;
        do {
            commandLine = scanner. nextLine();
            if (commandLine.equalsIgnoreCase("exit")) {
                break;
            }
            ui.executeCommand(commandLine);
        } while (true);
    }
}
