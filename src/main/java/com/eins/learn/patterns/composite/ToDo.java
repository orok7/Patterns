package com.eins.learn.patterns.composite;

public class ToDo implements ToDoList {
    private String text;

    public ToDo(String text) {
        this.text = text;
    }

    @Override
    public String getHtml() {
        return text;
    }
}
