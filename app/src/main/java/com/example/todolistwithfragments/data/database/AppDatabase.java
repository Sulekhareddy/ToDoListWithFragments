package com.example.todolistwithfragments.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.todolistwithfragments.data.dao.ToDoItemDao;
import com.example.todolistwithfragments.data.entities.ToDoItem;

@Database(entities = {ToDoItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public static final String DB_NAME = "todoitem.db";


    public abstract ToDoItemDao toDoItemDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }
}
