package com.example.todolistwithfragments.data.repo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.todolistwithfragments.data.dao.ToDoItemDao;
import com.example.todolistwithfragments.data.database.AppDatabase;
import com.example.todolistwithfragments.data.entities.ToDoItem;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {

    private ToDoItemDao toDoItemDao;

    public Repository(Context context) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(context.getApplicationContext());
        toDoItemDao = appDatabase.toDoItemDao();
    }

    public List<ToDoItem> getAllToDoItems() throws ExecutionException, InterruptedException {
        return new GetAllToDoItemsAsyncTask(toDoItemDao).execute().get();
    }

    public void insertToDoItem(ToDoItem toDoItem) {
        new InsertToDoListAsyncTask(toDoItemDao).execute(toDoItem);
    }

    public void onDestroy() {
        AppDatabase.destroyInstance();
    }

    private static class InsertToDoListAsyncTask extends AsyncTask<ToDoItem, Void, Void> {

        private ToDoItemDao toDoItemDao;

        public InsertToDoListAsyncTask(ToDoItemDao toDoItemDao) {
            this.toDoItemDao = toDoItemDao;
        }

        @Override
        protected Void doInBackground(ToDoItem... toDoItems) {
            toDoItemDao.addToDoItem(toDoItems[0]);
            return null;
        }
    }

    private static class GetAllToDoItemsAsyncTask extends AsyncTask<Void, Void, List<ToDoItem>>{

        private ToDoItemDao toDoItemDao;

        public GetAllToDoItemsAsyncTask(ToDoItemDao toDoItemDao) {
            this.toDoItemDao = toDoItemDao;
        }

        @Override
        protected List<ToDoItem> doInBackground(Void... voids) {
            return toDoItemDao.getAllToDoItems();
        }
    }
}

