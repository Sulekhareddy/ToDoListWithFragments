package com.example.todolistwithfragments.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todolistwithfragments.R;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
    }

    @Override
    public void onFragmentInteraction() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToDoListFragment())
                .addToBackStack(null)
                .commit();
    }
}
