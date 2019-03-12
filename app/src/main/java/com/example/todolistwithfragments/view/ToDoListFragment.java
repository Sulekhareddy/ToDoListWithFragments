package com.example.todolistwithfragments.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ToDoListFragment extends Fragment  implements OnItemClickedListener {

    private EditText toDoList_EditText;
    private Button addButton;

    private RecyclerView recyclerView;
    private ToDoListAdapter adapter;
    private List<ToDoItem> toDOList = new ArrayList<>();
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
                    int topPos = 0;
                    toDoItem = new ToDoItem(toDoItemsData);
                    toDOList.add(topPos, toDoItem);
                    toDoList_EditText.setText("");
                    adapter.notifyItemInserted(topPos);
                    repository.insertToDoItem(toDoItem);
                    recyclerView.scrollToPosition(topPos);
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
        List<ToDoItem> toDoItems = repository.getAllToDoItems();
        toDOList = toDoItems;
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
