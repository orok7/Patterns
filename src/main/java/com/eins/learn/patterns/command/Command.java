package com.eins.learn.patterns.command;

public interface Command {
    void execute(String[] args) throws IllegalArgumentException;

    void undo(String[] args);
}