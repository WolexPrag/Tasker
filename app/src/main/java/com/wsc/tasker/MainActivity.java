package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.wsc.tasker.MVVM.*;
import com.wsc.tasker.task.*;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public class Buttons {
        public Buttons(View view) {
            this.home = view.findViewById(R.id.home_button);
            this.undo = view.findViewById(R.id.undo_button);
            this.redo = view.findViewById(R.id.redo_button);
        }
        Button home;
        Button redo;
        Button undo;
    }
    public void showErrorActivity(Exception error) {
        ErrorActivity.error = error;
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
        } catch (Exception e) {
            showErrorActivity(e);
        }
    }

}



