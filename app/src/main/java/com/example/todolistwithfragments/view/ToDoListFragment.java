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
import com.example.todolistwithfragments.listeners.OnItemClickedListener;
import com.example.todolistwithfragments.model.ToDoItem;
import com.example.todolistwithfragments.view.adapters.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends Fragment  implements OnItemClickedListener {

    private EditText toDoList_EditText;
    private Button addButton;
    private Button resetButton;

    private RecyclerView recyclerView;
    private ToDoListAdapter adapter;
    private List<ToDoItem> toDOList = new ArrayList<>();


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
        resetButton = view.findViewById(R.id.reset_button);

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new ToDoListAdapter();
        adapter.setAdapterItems(toDOList);
        adapter.setOnItemClickedListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoItemsData = toDoList_EditText.getText().toString();
                if(!toDoItemsData.isEmpty()){
                    int topPos = 0;
                    toDOList.add(topPos, new ToDoItem(toDoItemsData));
                    adapter.notifyItemInserted(topPos);
                    recyclerView.scrollToPosition(topPos);
                }else{
                    Toast.makeText(getContext(), "Please enter ToDo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoList_EditText.setText("");
            }
        });
        return view;
    }

    @Override
    public void onToDoListItemClicked(ToDoItem toDoItem) {

        Toast.makeText(getContext(),  toDoItem.getToDoItems() + " has clicked", Toast.LENGTH_SHORT).show();
    }

}
