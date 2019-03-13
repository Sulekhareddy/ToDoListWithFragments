package com.example.todolistwithfragments.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistwithfragments.R;
import com.example.todolistwithfragments.data.repo.Repository;
import com.example.todolistwithfragments.listeners.OnItemClickedListener;
import com.example.todolistwithfragments.data.entities.ToDoItem;
import com.example.todolistwithfragments.view.adapters.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ToDoListFragment extends Fragment  implements OnItemClickedListener {

    private EditText toDoList_EditText;
    private Button addButton;

    private RecyclerView recyclerView;
    private ToDoListAdapter adapter;
    ToDoItem toDoItem;
    private Repository repository;

    public ToDoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        toDoList_EditText = view.findViewById(R.id.list_editText);
        addButton = view.findViewById(R.id.add_button);

        repository = new Repository(getContext());

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new ToDoListAdapter();
        adapter.setOnItemClickedListener(this);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toDoItemsData = toDoList_EditText.getText().toString();
                if(!toDoItemsData.isEmpty()){

                    toDoItem = new ToDoItem();
                    toDoItem.setToDoItems(toDoItemsData);
                    adapter.addItem(0, toDoItem);
                    toDoList_EditText.setText("");
                    repository.insertToDoItem(toDoItem);
                }else{
                    Toast.makeText(getContext(), getContext().getResources().getString(R.string.general_text), Toast.LENGTH_SHORT).show();
                }

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<ToDoItem> toDoItems = null;
        try {
            toDoItems = repository.getAllToDoItems();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        adapter.setAdapterItems(toDoItems);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repository.onDestroy();
    }

    @Override
    public void onToDoListItemClicked(ToDoItem toDoItem) {

        Toast.makeText(getContext(),  toDoItem.getToDoItems() + getContext().getResources().getString(R.string.clicked), Toast.LENGTH_SHORT).show();
    }

}
