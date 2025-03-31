package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

    Buttons buttons;

    MainFragmentTaskSpace mainFragmentTaskSpace;
    TaskSpace taskSpace;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Awake(savedInstanceState);
            Start(savedInstanceState);
        } catch (Exception e) {
            showErrorActivity(e);
        }


    }

    public void Awake(Bundle savedInstanceState) {
        taskSpace = new TaskSpace();

        taskSpace.setTasks(getTestTasks());

        mainFragmentTaskSpace = MainFragmentTaskSpace.getInstance();


    }

    public void Start(Bundle savedInstanceState) {

        showFragmentMainView(savedInstanceState, mainFragmentTaskSpace);
    }

    private static List<Task> getTestTasks() {
        List<Task> ret = new ArrayList<Task>();
        for (int i = 0; i <= 100; i++) {
            Task task = new TaskForTest();
            task.setName("Netu day: " + i);
            task.setDescription("Netu I don't know why " + i + " is absence");
            ret.add(task);
        }
        return ret;
    }


    public void showFragmentMainView(Bundle savedInstanceState, Fragment fragment) {
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }

    public void showErrorActivity(Exception error) {
        ErrorActivity.error = error;
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
    }

}



