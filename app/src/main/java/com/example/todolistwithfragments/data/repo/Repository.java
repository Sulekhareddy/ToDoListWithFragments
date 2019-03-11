package com.example.todolistwithfragments.data.repo;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.todolistwithfragments.data.dao.ToDoItemDao;
import com.example.todolistwithfragments.data.database.AppDatabase;
import com.example.todolistwithfragments.data.entities.ToDoItem;

import java.util.List;

public class Repository {

    private ToDoItemDao toDoItemDao;

    public Repository(Context context) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context.getApplicationContext());
        toDoItemDao = appDatabase.toDoItemDao();
    }

    public List<ToDoItem> getAllToDoItems() {
        return toDoItemDao.getAllToDoItems();
    }

    public void insertToDoItem(ToDoItem toDoItem) {
        new insertToDoListAsyncTask(toDoItemDao).execute(toDoItem);
    }

    public void onDestroy() {
        AppDatabase.destroyInstance();
    }

    private static class insertToDoListAsyncTask extends AsyncTask<ToDoItem, Void, Void> {

        private ToDoItemDao toDoItemDao;

        public insertToDoListAsyncTask(ToDoItemDao toDoItemDao) {
            this.toDoItemDao = toDoItemDao;
        }

        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            toDoItemDao.addToDoItem(toDoItems[0]);
            return null;
        }
    }
}

