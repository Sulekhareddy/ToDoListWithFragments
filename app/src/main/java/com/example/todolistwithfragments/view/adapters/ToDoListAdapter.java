package com.example.todolistwithfragments.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistwithfragments.R;
import com.example.todolistwithfragments.listeners.OnItemClickedListener;
import com.example.todolistwithfragments.data.entities.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListViewHolder>{

    private OnItemClickedListener onItemClickedListener;

    private List<ToDoItem> todolist = new ArrayList<>();

    public ToDoListAdapter() {
    }

    @NonNull
    @Override
    public ToDoListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ToDoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoListViewHolder toDoListViewHolder, int position) {
        final ToDoItem toDoItem = todolist.get(position);
        toDoListViewHolder.bind(toDoItem, onItemClickedListener);
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    public void setAdapterItems(List<ToDoItem> todoitem) {
        if (!todolist.isEmpty()) {
            todolist.clear();
        }
        todolist = todoitem;
    }


    public void setOnItemClickedListener(OnItemClickedListener listener){
        onItemClickedListener = listener;
    }
}
