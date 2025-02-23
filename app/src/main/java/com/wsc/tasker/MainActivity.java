package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
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
            this.home = findViewById(R.id.home_button);
            this.undo = findViewById(R.id.undo_button);
            this.redo = findViewById(R.id.redo_button);

        }

        Button home;
        Button redo;
        Button undo;
    }

    public class Mode<T1> {
        public Mode(T1 viewModel, Fragment fragment) {
            this.viewModel = viewModel;
            this.fragment = fragment;
        }

        T1 viewModel;
        Fragment fragment;

        public void replaceFragment() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

    Buttons buttons;
    List<TaskSpace> taskSpaces;

    Mode<IMainModeViewModel> mainMode;
    Mode<IEditorModeViewModel> editorMode;

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
        buttons = new Buttons();
        taskSpaces = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task().setName("tam param"));
        taskSpaces.add(new TaskSpace(tasks));

        LocalMainModeViewModel viewModel = new LocalMainModeViewModel(getTaskSpace());
        mainMode = new Mode<>(viewModel,  MainFragmentMainActivity.newInstance(viewModel));
    }

    public TaskSpace getTaskSpace() {
        return taskSpaces.get(0);
    }

    public void Start(Bundle savedInstanceState) {
        mainMode.replaceFragment();
    }
    public void showErrorActivity(Exception error) {
        ErrorActivity.error = error;
        Intent intent = new Intent(this, ErrorActivity.class);
        startActivity(intent);
    }
}



