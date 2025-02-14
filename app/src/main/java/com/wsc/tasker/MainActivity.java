package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.wsc.tasker.MVVM.ITaskMenuViewModel;
import com.wsc.tasker.MVVM.LocalTasksMenuViewModel;
import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.event.LocalSingleSubscriber;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskAdapter;
import com.wsc.tasker.task.TaskSpace;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity {
    public class ButtonsMainActivity {
        public ButtonsMainActivity(){
            this.home = findViewById(R.id.home_button);
            this.undo = findViewById(R.id.undo_button);
            this.redo = findViewById(R.id.redo_button);

            this.add = findViewById(R.id.add_button);
            this.select = findViewById(R.id.select_button);
            this.edit = findViewById(R.id.edit_button);
            this.filter = findViewById(R.id.filter_button);
            this.analyze = findViewById(R.id.analyze_button);
            this.other = findViewById(R.id.other_button);
        }
        Button home;
        Button redo;
        Button undo;

        Button add;
        Button select;
        Button edit;
        Button filter;
        Button analyze;
        Button other;
    }

    ButtonsMainActivity buttons;

    RecyclerView taskRecyclerView;
    TaskAdapter taskAdapter;

    List<TaskSpace> taskSpaces;
    LocalTasksMenuViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Awake(savedInstanceState);
        } catch (Exception e) {
            ErrorActivity.error = e;
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }


    }

    public void Awake(Bundle savedInstanceState) {
        buttons = new ButtonsMainActivity();
        taskRecyclerView = findViewById(R.id.task_layout);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        taskRecyclerView.setAdapter(taskAdapter);
        taskSpaces = new ArrayList<>();
        Start(savedInstanceState);
    }

    public void Start(Bundle savedInstanceState) {
        TaskSpace taskSpace = new TaskSpace();
        taskSpaces.add(taskSpace);

        viewModel = new LocalTasksMenuViewModel(taskSpaces.get(0));


        ISubscriber<List<Task>> updateTasksSubscriber = getSubscriberForTaskAdapter(taskAdapter, viewModel);
        viewModel.subscribeOnUpdateTasks(updateTasksSubscriber);
        buttons.add.setOnClickListener(v -> {
            viewModel.createNewTask();
        });
    }

    public ISubscriber<List<Task>> getSubscriberForTaskAdapter(TaskAdapter taskAdapter, ITaskMenuViewModel viewModel) {
        return new LocalSingleSubscriber<List<Task>>(new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(@NonNull List<Task> tasks) {
                taskAdapter.setTasks(tasks);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}


