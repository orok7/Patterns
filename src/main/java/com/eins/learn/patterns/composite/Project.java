package com.eins.learn.patterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Project implements ToDoList {
    private String title;
    private List<ToDoList> todos = new ArrayList<>();

    public Project(String title) {
        this.title = title;
    }

    public void add(ToDoList toDoList) {
        todos.add(toDoList);
    }

    public void remove(ToDoList toDoList) {
        todos.remove(toDoList);
    }

    @Override
    public String getHtml() {
        StringBuilder html = new StringBuilder("<h2>" + title + "</h2>\n<ul>\n");
        for (ToDoList todo : todos) {
            html.append("<li>").append(todo.getHtml()).append("</li>\n");
        }
        html.append("</ul>\n");
        return html.toString();
    }
}
