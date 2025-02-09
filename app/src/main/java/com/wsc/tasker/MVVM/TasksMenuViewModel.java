package com.wsc.tasker.MVVM;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import com.wsc.tasker.ErrorActivity;
import com.wsc.tasker.MainActivity;
import com.wsc.tasker.R;
import com.wsc.tasker.event.INotifier;
import com.wsc.tasker.event.ISubscriber;
import com.wsc.tasker.event.LocalNotifier;
import com.wsc.tasker.event.LocalSingleSubscriber;
import com.wsc.tasker.task.Task;
import com.wsc.tasker.task.TaskSpace;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TasksMenuViewModel extends ViewModel {
    private TaskSpace model;
    private ISubscriber<List<Task>> updateTaskSubscriber;
    private INotifier<List<Task>> updateTaskNotifier;
    private INotifier<String> errorNotifier;
    private List<Task> tasks;
    private List<Task> selected;

    public TasksMenuViewModel(TaskSpace model) {
        this.model = model;
        updateTaskNotifier = new LocalNotifier<>();
        updateTaskSubscriber = new LocalSingleSubscriber<>(new Observer<List<Task>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                setTasks(model.getCopyTask());
            }

            @Override
            public void onNext(@NonNull List<Task> tasks) {
                setTasks(tasks);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                errorNotifier.notify(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        model.subscribe(updateTaskSubscriber);
    }

    public void setTasks(List<Task> tasks) {
        tasks = new ArrayList<>(tasks);
        updateTaskNotifier.notify(getCopyTasks());
    }

    public void addTask(Task selected) {
        this.selected.add(selected);
        updateTaskNotifier.notify(getCopyTasks());
    }

    public void removeTask(Task task) {
        this.selected.remove(task);
        updateTaskNotifier.notify(getCopyTasks());
    }

    public List<Task> getCopyTasks() {
        return new ArrayList<>(tasks);
    }

    public void addSelected(Task selected) {
        this.selected.add(selected);
    }

    public void removeSelected(Task selected) {
        this.selected.remove(selected);
    }

    public List<Task> getCopySelected() {
        return new ArrayList<>(selected);
    }


}
