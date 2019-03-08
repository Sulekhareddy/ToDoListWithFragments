package com.example.todolistwithfragments.model;

public class ToDoItem {

    public ToDoItem(String toDoItems) {
        this.toDoItems = toDoItems;
    }

    private String toDoItems;

    public String getToDoItems() {
        return toDoItems;
    }

    public void setToDoItems(String toDoitems) {
        this.toDoItems = toDoitems;
    }
}
