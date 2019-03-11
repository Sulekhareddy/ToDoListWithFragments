package com.example.todolistwithfragments.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistwithfragments.R;

public class LoginFragment extends Fragment implements View.OnClickListener{

    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button submitButton;
    private Button resetButton;
    private SharedPreferences sharedPreferences;
    public static final String APP_SHARED_PREF = "APP_PREF";
    public static final String PREF_KEY_USERNAME = "USER_NAME";
    public static final String PREF_KEY_PWD = "PASSWORD";
    public static final String PREF_KEY_USER_LOGGED_IN = "LOGGED_IN";
    private String prefUserName;
    private String prefPwd;
    private boolean prefIsAlreadyLoggedIn;

    private OnFragmentInteractionListener listener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userNameEditText = view.findViewById(R.id.user_name_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        submitButton = view.findViewById(R.id.submit_button);
        resetButton = view.findViewById(R.id.reset_button);

        submitButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
         return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit_button:

                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!userName.isEmpty() && !password.isEmpty()) {
                    if (prefIsAlreadyLoggedIn) {
                        if (userName.equals(prefUserName) && password.equals(prefPwd)) {

                            onButtonPressed();
                        } else {
                            Toast.makeText(getActivity(), "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        saveUserName(userName, password);
                       onButtonPressed();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter the login details", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.reset_button:

                userNameEditText.setText("");
                passwordEditText.setText("");
                break;
        }

    }

    public void onButtonPressed() {
        if (listener != null) {
            listener.onFragmentInteraction();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = getActivity().getSharedPreferences(APP_SHARED_PREF, Context.MODE_PRIVATE);
        prefUserName = sharedPreferences.getString(PREF_KEY_USERNAME, "");
        prefPwd = sharedPreferences.getString(PREF_KEY_PWD, "");
        prefIsAlreadyLoggedIn = sharedPreferences.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void saveUserName(String userName, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_KEY_USERNAME, userName);
        editor.putString(PREF_KEY_PWD, password);
        editor.putBoolean(PREF_KEY_USER_LOGGED_IN, true);
        editor.apply();
    }

}
