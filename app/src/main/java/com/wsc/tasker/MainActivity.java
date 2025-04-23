package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wsc.tasker.MVVM.MainViewMode.IMainViewModeViewModel;
import com.wsc.tasker.MVVM.MainViewMode.LocalMainViewModeVM;
import com.wsc.tasker.task.ITaskSpaceGiver;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskForTest;
import com.wsc.tasker.task.TestTaskSpaceGiver;

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
    ITaskSpaceGiver taskSpaceGiver;
    MainViewModeFragment mainViewModeFragment;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            View view = findViewById(R.id.main_panel);
            Awake(savedInstanceState);
            initButtons(view);
            Start(savedInstanceState);

        } catch (Exception e) {
            showErrorActivity(e);
        }


    }

    public void Awake(Bundle savedInstanceState) {
        initGiver();
        initMainViewMode();
    }
    public void initButtons(View view){
        buttons = new Buttons(view);
    }
    public void initGiver(){
        taskSpaceGiver = new TestTaskSpaceGiver();
    }
    public void initMainViewMode(){
        mainViewModeFragment = MainViewModeFragment.getInstance(new LocalMainViewModeVM());
    }

    public void Start(Bundle savedInstanceState) {

        showOnContainer(savedInstanceState, mainViewModeFragment);
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


    public void showOnContainer(Bundle savedInstanceState, Fragment fragment) {
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



