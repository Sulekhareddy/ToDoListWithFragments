package com.example.todolistwithfragments.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.todolistwithfragments.data.entities.ToDoItem;

import java.util.List;

@Dao
public interface ToDoItemDao {

    @Query("SELECT * FROM todoitem_table ORDER BY id DESC")
    List<ToDoItem> getAllToDoItems();

    @Query("SELECT COUNT(*) FROM todoitem_table")
    int getToDoItemCount();

    @Insert
    void addToDoItem(ToDoItem toDoItem);

    @Delete
    void removeToDoItem(ToDoItem toDoItem);
}

