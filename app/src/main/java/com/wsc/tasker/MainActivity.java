package com.wsc.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Start(savedInstanceState);
        } catch (Exception e) {
            ErrorActivity.error = e;
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }


    }

    public void Start(Bundle savedInstanceState) {
        Button homeButton = findViewById(R.id.home_button);
        Button undoButton = findViewById(R.id.undo_button);
        Button redoButton = findViewById(R.id.redo_button);

        Button addButton = findViewById(R.id.add_button);
        Button selectButton = findViewById(R.id.select_button);
        Button editButton = findViewById(R.id.edit_button);
        Button filterButton = findViewById(R.id.filter_button);
        Button analyzeButton = findViewById(R.id.analyze_button);
        Button otherButton = findViewById(R.id.other_button);
        List<TaskSpace> taskSpaces = new ArrayList<>();
        TaskSpace taskSpace = new TaskSpace();
        taskSpaces.add(taskSpace);

        LocalTasksMenuViewModel viewModel = new LocalTasksMenuViewModel(taskSpaces.get(0));
        TaskAdapter taskAdapter = new TaskAdapter();
        ISubscriber<List<Task>> updateTasksSubscriber = getSubscriberForTaskAdapter(taskAdapter,viewModel);
        viewModel.subscribeOnUpdateTasks(updateTasksSubscriber);
        addButton.setOnClickListener(v->{
            viewModel.addTask(new Task());
        });
    }
    public ISubscriber<List<Task>> getSubscriberForTaskAdapter(TaskAdapter taskAdapter, ITaskMenuViewModel viewModel){
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


