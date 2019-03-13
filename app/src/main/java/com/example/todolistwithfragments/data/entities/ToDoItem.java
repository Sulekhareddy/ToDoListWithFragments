package com.example.todolistwithfragments.data.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "todoitem_table")
public class ToDoItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "todo_items")
    private String toDoItems;

    public String getToDoItems() {
        return toDoItems;
    }

    public void setToDoItems(String toDoitems) {
        this.toDoItems = toDoitems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
