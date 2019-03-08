package com.example.todolistwithfragments.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.todolistwithfragments.R;
import com.example.todolistwithfragments.listeners.OnItemClickedListener;
import com.example.todolistwithfragments.model.ToDoItem;

public class ToDoListViewHolder extends RecyclerView.ViewHolder {

    TextView toDoTextView;

    public ToDoListViewHolder(@NonNull View itemView) {
        super(itemView);

        toDoTextView = itemView.findViewById(R.id.toDo_thing);
    }

    public void bind(final ToDoItem toDoItem, final OnItemClickedListener onItemClickedListener) {
        toDoTextView.setText(toDoItem.getToDoItems());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickedListener.onToDoListItemClicked(toDoItem);
            }
        });
    }
}

